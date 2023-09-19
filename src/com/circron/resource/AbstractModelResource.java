package com.circron.resource;

import com.circron.resource.EntityEventResource.EntityEvent;
import com.circron.resource.EntityStatusResource.EntityStatus;
import com.circron.util.DeleteOnCloseFileInputStream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class AbstractModelResource<T> extends AbstractResource<T> {
    private static final Object ACCESS_LOCK = new Object();
    private EntityEventResource entityEventResource;
    private EntityStatusResource entityStatusResource;

    AbstractModelResource(Class<T> clazz) {
        super(clazz);
    }

    EntityEventResource getEntityEventResource() {
        if (entityEventResource == null) {
            synchronized (ACCESS_LOCK) {
                if (entityEventResource == null) {
                    entityEventResource = getResourceContext().getResource(EntityEventResource.class);
                }
            }
        }
        return entityEventResource;
    }

    EntityStatusResource getEntityStatusResource() {
        if (entityStatusResource == null) {
            synchronized (ACCESS_LOCK) {
                if (entityStatusResource == null) {
                    entityStatusResource = getResourceContext().getResource(EntityStatusResource.class);
                }
            }
        }
        return entityStatusResource;
    }

    private String eventToState(String event) {
        if (EntityEvent.ENTITY_EVENT_DELETED.equalsIgnoreCase(event)) {
            return EntityStatus.STATE_INACTIVE;
        } else if (EntityEvent.ENTITY_EVENT_ARCHIVED.equalsIgnoreCase(event)) {
            return EntityStatus.STATE_ARCHIVED;
        } else {
            return EntityStatus.STATE_ACTIVE;
        }
    }

    private void addEvent(T element, String application, Long systemLoginId, String event) {
        if (getEntityType() == null || getEntityType().value() <= 0) {
            return;
        }
        final String state = eventToState(event);

        try {
            long entityId = ((Number) getIdField().get(element)).longValue();
            if (getEntityType().recordEvents()) {
                getEntityEventResource().add(new EntityEvent(getEntityType().value(), entityId, application, systemLoginId, event));
            }

            if (getEntityType().recordStatus()) {
                EntityStatus status = getEntityStatusResource().get(getEntityType().value(), entityId);
                if (status == null) {
                    status = new EntityStatus();
                    status.setEntityId(entityId);
                    status.setEntityTypeId(getEntityType().value());
                    status.setCreateDate(new Date());
                    status.setCreateSystemLoginId(systemLoginId);
                }

                status.setUpdateDate(new Date());
                status.setUpdateSystemLoginId(systemLoginId);
                status.setState(state);

                if (status.getEntityStatusId() == null) {
                    getEntityStatusResource().add(status);
                } else {
                    getEntityStatusResource().update(status);
                }
            }
        } catch (Throwable t) {
            getLog().error("Unable to add entity event.", t);
        }

    }

    private void addEvent(List<T> elements, String application, Long systemLoginId, String event) {
        if (getEntityType() == null || getEntityType().value() <= 0) {
            return;
        }
        final String state = eventToState(event);

        try {
            List<Long> entityIds = new ArrayList<>();
            List<EntityEvent> events = new ArrayList<>();
            for (T element : elements) {
                long entityId = ((Number) getIdField().get(element)).longValue();
                if (getEntityType().recordEvents()) {
                    events.add(new EntityEvent(getEntityType().value(), entityId, application, systemLoginId, event));
                }
                entityIds.add(entityId);
            }

            if (getEntityType().recordEvents()) {
                getEntityEventResource().add(events);
            }

            if (getEntityType().recordStatus()) {
                List<EntityStatus> entityStatuses = getEntityStatusResource().get(getEntityType().value(), entityIds, null, null);
                List<EntityStatus> newEntityStatuses = new ArrayList<>();

                for (Long entityId : entityIds) {

                    EntityStatus existingStatus = null;
                    for (EntityStatus status : entityStatuses) {
                        if (status.getEntityId().equals(entityId)) {
                            existingStatus = status;
                            break;
                        }
                    }

                    if (existingStatus == null) {
                        existingStatus = new EntityStatus();
                        existingStatus.setState(state);
                        existingStatus.setEntityId(entityId);
                        existingStatus.setEntityTypeId(getEntityType().value());
                        existingStatus.setCreateDate(new Date());
                        existingStatus.setCreateSystemLoginId(systemLoginId);
                        existingStatus.setUpdateDate(new Date());
                        existingStatus.setUpdateSystemLoginId(systemLoginId);
                        newEntityStatuses.add(existingStatus);
                    }
                }

                if (!newEntityStatuses.isEmpty()) {
                    getEntityStatusResource().add(newEntityStatuses);
                }

                if (entityStatuses != null && !entityStatuses.isEmpty()) {
                    for (EntityStatus status : entityStatuses) {
                        status.setUpdateDate(new Date());
                        status.setUpdateSystemLoginId(systemLoginId);
                        status.setState(state);
                    }

                    getEntityStatusResource().update(entityStatuses);
                }
            }
        } catch (Throwable t) {
            getLog().error("Unable to add entity event.", t);
        }
    }

    @GET
    public T get(@QueryParam("id") Long id) {
        getLog().debug("Fetching " + getModelClass().getName() + " by id, '" + id + "'.");
        return super.get(id);
    }

    @GET
    @Path("/getmultiple")
    public List<T> getMultiple(@QueryParam("id") List<Long> ids) {
        getLog().debug("Fetching " + getModelClass().getName() + " by multiples id, " + ids.size() + " ids.");
        List<T> rtnList = new ArrayList<>();
        for (Long id : ids) {
            T obj = super.get(id);
            if (obj != null) {
                rtnList.add(obj);
            }
        }
        return rtnList;
    }

    @GET
    @Path("/all")
    public List<T> getAll(@QueryParam("fr") Long firstRecord, @QueryParam("rc") Long recordCount) {
        getLog().debug("Fetching all " + getModelClass().getName() + ", First Record: " + firstRecord + ", Record Count: " + recordCount + ".");
        return super.getAll(firstRecord, recordCount);
    }

    @POST
    @Path("/add")
    public T add(T ip, @QueryParam("slid") Long systemLoginId, @QueryParam("app") String application) {
        getLog().debug("Adding one " + getModelClass().getName() + ".");
        T newElement = super.add(ip);
        addEvent(newElement, application, systemLoginId, EntityEvent.ENTITY_EVENT_CREATED);
        return newElement;
    }

    @POST
    @Path("/addall")
    public List<T> add(List<T> ips, @QueryParam("slid") Long systemLoginId, @QueryParam("app") String application) {
        getLog().debug("Adding " + ips.size() + "x " + getModelClass().getName() + ".");
        List<T> newElements = super.add(ips);
        addEvent(newElements, application, systemLoginId, EntityEvent.ENTITY_EVENT_CREATED);
        return newElements;
    }

    @PUT
    @Path("/remove")
    public void remove(T element, @QueryParam("slid") Long systemLoginId, @QueryParam("app") String application) {
        getLog().debug("Removing one " + getModelClass().getName() + ".");
        addEvent(element, application, systemLoginId, EntityEvent.ENTITY_EVENT_DELETED);
    }

    @PUT
    @Path("/removebyid")
    public void removeById(@QueryParam("id") Long id, @QueryParam("slid") Long systemLoginId, @QueryParam("app") String application) {
        getLog().debug("Removing one " + getModelClass().getName() + ".");
        addEvent(get(id), application, systemLoginId, EntityEvent.ENTITY_EVENT_DELETED);
    }

    @PUT
    @Path("/removeall")
    public void remove(List<T> elements, @QueryParam("slid") Long systemLoginId, @QueryParam("app") String application) {
        getLog().debug("Removing " + elements.size() + "x " + getModelClass().getName() + ".");
        addEvent(elements, application, systemLoginId, EntityEvent.ENTITY_EVENT_DELETED);
    }

    @PUT
    @Path("/update")
    public void update(T element, @QueryParam("slid") Long systemLoginId, @QueryParam("app") String application) {
        getLog().debug("Updating one " + getModelClass().getName() + ".");
        super.update(element);
        addEvent(element, application, systemLoginId, EntityEvent.ENTITY_EVENT_UPDATED);
    }

    @PUT
    @Path("/updateall")
    public void update(List<T> elements, @QueryParam("slid") Long systemLoginId, @QueryParam("app") String application) {
        getLog().debug("Updating " + elements.size() + "x " + getModelClass().getName() + ".");
        super.update(elements);
        addEvent(elements, application, systemLoginId, EntityEvent.ENTITY_EVENT_UPDATED);
    }

    @GET
    @Path("/events")
    public List<EntityEvent> getEventHistory(@QueryParam("id") List<Long> ids, @QueryParam("fr") Long first, @QueryParam("rc") Long count) {
        return getEntityEventResource().get(getEntityType().value(), ids, first, count);
    }

    @GET
    @Path("/status")
    public List<EntityStatus> getEventStatus(@QueryParam("id") List<Long> ids, @QueryParam("fr") Long first, @QueryParam("rc") Long count) {
        return getEntityStatusResource().get(getEntityType().value(), ids, first, count);
    }

    @PUT
    @Path("/addevent")
    public List<EntityEvent> addCustomEvent(@QueryParam("id") List<Long> ids, @QueryParam("event") String eventText, @QueryParam("slid") Long systemLoginId, @QueryParam("app") String application) {
        List<EntityEvent> events = new ArrayList<>();
        for (Long id : ids) {
            events.add(new EntityEvent(getEntityType().value(), id, application, systemLoginId, eventText));
        }
        return getEntityEventResource().add(events);
    }

    @GET
    @Path("/csv")
    public Response getFile(@QueryParam("id") List<Long> ids, @QueryParam("filename") String fileName) throws IOException {
        StringBuilder where = null;
        if (ids != null && !ids.isEmpty()) {
            if (ids.size() == 1) {
                where = new StringBuilder(this.getIdColumnName() + " = " + ids.get(0));
            } else {
                where = new StringBuilder(this.getIdColumnName() + " IN ( " + ids.get(0));
                for (int i = 1; i < ids.size(); i++) {
                    where.append(", ").append(ids.get(i));
                }
                where.append(") ");
            }
        }
        assert where != null;
        File file = this.exportToFile(where.toString());
        ResponseBuilder response = Response.ok();
        if (fileName != null) {
            response.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        } else {
            response.header("Content-Disposition", "attachment; filename=\"" + getModelClass().getName() + ".csv\"");
        }
        response.type("text/csv");
        response.entity(new DeleteOnCloseFileInputStream(file));
        return response.build();
    }

}