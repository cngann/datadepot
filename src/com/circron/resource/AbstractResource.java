package com.circron.resource;

import com.circron.hibernate.HibernateSessionFactory;
import com.circron.meta.EntityType;
import com.circron.resource.EntityStatusResource.EntityStatus;
import com.circron.util.PropertyUtil;
import com.sun.jersey.api.core.ResourceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
/**
 * An abstract resource inherited by all other resources.
 *
 * @author cngann
 *
 */
public abstract class AbstractResource<T> {
    public static final File WORK_FOLDER = PropertyUtil.getInstance().getFile("files", "WORK_FOLDER");
    private final Log log;
    private Field idField;
    private final EntityType entityType;
    private static final int BATCH_SIZE = 40;
    private final Class<T> clazz;
    @Context
    private ResourceContext _resourceContext;

    AbstractResource(Class<T> clazz) {
        UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        this.clazz = clazz;
        entityType = getModelClass().getAnnotation(EntityType.class);

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                idField = field;
                idField.setAccessible(true);
                break;
            }
        }
        log = LogFactory.getLog(this.getClass());
    }

    Class<T> getModelClass() {
        return clazz;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Field getIdField() {
        return idField;
    }

    /**
     * Provides access to the log object for child classes to use.
     */
    Log getLog() {
        return log;
    }

    ResourceContext getResourceContext() {
        return _resourceContext;
    }

    /**
     * Gets the object with the given ID.
     */
    T get(Serializable id) {
        if (id == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }

        return getEqualsSingle(getIdField().getName(), id);
    }

    /**
     * Get objects of all these records in the database.
     */
    List<T> getAll() {
        return getAll(null, null, null);
    }

    /**
     * Get objects of all these records in the database.
     */
    List<T> getAll(String orderBy) {
        return getAll(null, null, orderBy);
    }

    /**
     * Get objects of all these records in the database.
     */
    List<T> getAll(Long first, Long count) {
        return getAll(first, count, null);
    }

    Criterion criteriaNotLogicallyDeleted(String entityTableAlias) {
        DetachedCriteria crit = DetachedCriteria.forClass(EntityStatus.class, "entity_status");
        crit.add(Restrictions.eq("entity_status.entityTypeId", getEntityType().value()));
        crit.add(Restrictions.ne("entity_status.state", EntityStatus.STATE_ACTIVE));
        crit.add(Property.forName(entityTableAlias + "." + getIdField().getName()).eqProperty("entity_status.entityId"));
        crit.setProjection(Projections.property("entity_status.state"));
        return Subqueries.notExists(crit);
    }

    Criterion criteriaUpdatedSince(String entityTableAlias, Date date) {
        final DetachedCriteria crit = DetachedCriteria.forClass(EntityStatus.class, "entity_status");
        crit.add(Restrictions.eq("entity_status.entityTypeId", getEntityType().value()));
        crit.add(Restrictions.gt("entity_status.updateDate", date));
        crit.add(Property.forName(entityTableAlias + "." + getIdField().getName()).eqProperty("entity_status.entityId"));
        crit.setProjection(Projections.property("entity_status.entityId"));
        return Subqueries.exists(crit);
    }

    /**
     * Get objects of all these records in the database.
     */
    @SuppressWarnings("unchecked")
    List<T> getAll(Long first, Long count, String orderBy) {
        Criteria crit = HibernateSessionFactory.getSession().createCriteria(clazz, "entity");
        if (count != null) {
            crit.setFetchSize(count.intValue());
        }
        if (first != null) {
            crit.setFirstResult(first.intValue());
        }
        if (orderBy != null) {
            crit.addOrder(Order.asc(orderBy));
        }

        if (getEntityType() != null) {
            crit.add(criteriaNotLogicallyDeleted("entity"));
        }

        return crit.list();
    }

    /**
     * Gets a list of objects where the given field is equal to the given value.
     */
    List<T> getEquals(String field, Object value) {
        return getEquals(field, value, null, null, null);
    }

    /**
     * Gets a list of objects where the given field is equal to the given value.
     */
    List<T> getEquals(String field, Object value, String orderBy) {
        return getEquals(field, value, null, null, orderBy);
    }

    /**
     * Gets a list of objects where the given field is equal to the given value.
     */
    List<T> getEquals(String field, Object value, Long first, Long count) {
        return getEquals(field, value, first, count, null);
    }

    /**
     * Gets a list of objects where the given field is equal to the given value.
     */
    @SuppressWarnings("unchecked")
    List<T> getEquals(String field, Object value, Long first, Long count, String orderBy) {
        if (field == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final Criteria crit = HibernateSessionFactory.getSession().createCriteria(clazz, "entity");
        if (value instanceof Collection) {
            crit.add(Restrictions.in(field, (Collection<?>) value));
        } else {
            crit.add(Restrictions.eq(field, value));
        }
        if (count != null) {
            crit.setFetchSize(count.intValue());
        }
        if (first != null) {
            crit.setFirstResult(first.intValue());
        }
        if (orderBy != null) {
            crit.addOrder(Order.asc(orderBy));
        }

        if (getEntityType() != null) {
            crit.add(criteriaNotLogicallyDeleted("entity"));
        }
        return crit.list();
    }

    /**
     * Gets a list of objects where the given field is equal to the given value.
     */
    @SuppressWarnings("unchecked")
    List<T> getEquals(List<String> fields, List<?> values, Long first, Long count, String orderBy) {
        if (fields == null || values == null || fields.size() != values.size()) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final Criteria crit = HibernateSessionFactory.getSession().createCriteria(clazz, "entity");
        for (int i = 0; i < fields.size(); i++) {
            if (values.get(i) instanceof Collection) {
                crit.add(Restrictions.in(fields.get(i), (Collection<?>) values.get(i)));
            } else {
                crit.add(Restrictions.eq(fields.get(i), values.get(i)));
            }

        }

        if (count != null) {
            crit.setFetchSize(count.intValue());
        }
        if (first != null) {
            crit.setFirstResult(first.intValue());
        }
        if (orderBy != null) {
            crit.addOrder(Order.asc(orderBy));
        }

        if (getEntityType() != null) {
            crit.add(criteriaNotLogicallyDeleted("entity"));
        }
        return crit.list();
    }

    /**
     * Gets a single object where the given field is equal to the given value.
     */
    @SuppressWarnings("unchecked")
    T getEqualsSingle(String field, Serializable value) {
        if (field == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final Criteria crit = HibernateSessionFactory.getSession().createCriteria(clazz, "entity");
        if (value instanceof Collection) {
            crit.add(Restrictions.in(field, (Collection<?>) value));
        } else {
            crit.add(Restrictions.eq(field, value));
        }

        if (getEntityType() != null) {
            crit.add(criteriaNotLogicallyDeleted("entity"));
        }
        return (T) crit.uniqueResult();
    }

    /**
     * Gets a list of objects where the given field is equal to the given value.
     */
    @SuppressWarnings("unchecked")
    T getEqualsSingle(List<String> fields, List<?> values) {
        if (fields == null || values == null || fields.size() != values.size()) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final Criteria crit = HibernateSessionFactory.getSession().createCriteria(clazz, "entity");
        for (int i = 0; i < fields.size(); i++) {
            if (values.get(i) instanceof Collection) {
                crit.add(Restrictions.in(fields.get(i), (Collection<?>) values.get(i)));
            } else {
                crit.add(Restrictions.eq(fields.get(i), values.get(i)));
            }

        }

        if (getEntityType() != null) {
            crit.add(criteriaNotLogicallyDeleted("entity"));
        }
        return (T) crit.uniqueResult();
    }

    /**
     * Adds the given object to the database.
     */
    T add(T object) {
        if (object == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final Session session = HibernateSessionFactory.getSession();
        session.setFlushMode(FlushMode.AUTO);
        final Transaction tr = session.beginTransaction();
        try {
            session.persist(object);
            tr.commit();
        } finally {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
        }
        return object;
    }

    /**
     * Adds the collection of objects to the database in batches.
     */
    List<T> add(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        return add(collection, BATCH_SIZE);
    }

    /**
     * Adds the collection of objects to the database in batches of the given
     * size.
     */
    List<T> add(Collection<T> collection, Integer batchSize) {
        if (collection == null || collection.isEmpty() || batchSize == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final List<T> rtnList = new ArrayList<>(collection.size());
        final Session session = HibernateSessionFactory.getSession();
        final Transaction tr = session.beginTransaction();
        try {
            int i = 0;
            for (T object : collection) {
                session.persist(object);
                rtnList.add(object);
                if (i >= batchSize) {
                    session.flush();
                    session.clear();
                    i = 0;
                } else {
                    i++;
                }
            }
            tr.commit();
        } finally {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
        }
        return rtnList;
    }

    /**
     * Removes the given object from the DB.
     */
    void delete(T object) {
        if (object == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final Session session = HibernateSessionFactory.getSession();
        session.setFlushMode(FlushMode.AUTO);
        final Transaction tr = session.beginTransaction();
        try {
            session.delete(object);
            tr.commit();
        } catch (HibernateException t) {
            log.error("Error Updating Domain Object", t);
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        } finally {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
        }
    }

    /**
     * Removes the given objects from the database.
     */
    void delete(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        delete(collection, BATCH_SIZE);
    }

    /**
     * Removes the given objects from the database in batches of the given size.
     */
    void delete(Collection<T> objects, Integer batchSize) {
        if (objects == null || objects.isEmpty() || batchSize == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final Session session = HibernateSessionFactory.getSession();
        final Transaction tr = session.beginTransaction();
        try {
            int i = 0;
            for (T object : objects) {
                session.delete(object);
                if (i >= batchSize) {
                    session.flush();
                    session.clear();
                    i = 0;
                } else {
                    i++;
                }
            }
            tr.commit();
        } catch (HibernateException t) {
            log.error("Error Deleting Domain Object Collection", t);
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        } finally {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
        }
    }

    /**
     * Updates the given object in the database.
     */
    void update(T object) {
        if (object == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final Session session = HibernateSessionFactory.getSession();
        session.setFlushMode(FlushMode.AUTO);
        final Transaction tr = session.beginTransaction();
        try {
            session.update(object);
            tr.commit();
        } finally {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
        }
    }

    /**
     * Updates the given object in the database.
     */
    void update(Collection<T> objects) {
        if (objects == null || objects.isEmpty()) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        update(objects, BATCH_SIZE);
    }

    /**
     * Updates the given object in the database in batches of the given size.
     */
    void update(Collection<T> objects, Integer batchSize) {
        if (objects == null || objects.isEmpty() || batchSize == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        final Session session = HibernateSessionFactory.getSession();
        final Transaction tr = session.beginTransaction();
        try {
            int i = 0;
            for (T object : objects) {
                session.update(object);
                if (i >= batchSize) {
                    session.flush();
                    session.clear();
                    i = 0;
                } else {
                    i++;
                }
            }
            tr.commit();
        } finally {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
        }
    }

    File exportToFile(String whereClause) {
        return this.exportToFile(whereClause, "*");
    }

    File exportToFile(String whereClause, String fields) {
        final Session session = HibernateSessionFactory.getSession();
        final Transaction tr = session.beginTransaction();
        File file = new File(WORK_FOLDER, System.nanoTime() + "_" + Thread.currentThread().getName());
        try {

            String sql = "COPY (SELECT " + fields + " FROM " + getTableName() + " e WHERE NOT EXISTS  ( SELECT es.state from entity_status es where es.entity_type_id=";
            sql += getEntityType().value();
            sql += " AND es.state<>'ACTIVE' AND e." + getIdColumnName() + " = es.entity_id	)";
            if (whereClause != null) {
                sql += " AND " + whereClause;
            }
            sql += ") TO '" + file.getAbsolutePath() + "' DELIMITER ',' CSV HEADER;";
            log.debug("Exporting to file '" + file.getAbsolutePath() + "' ");
            log.debug(sql);
            session.createSQLQuery(sql).executeUpdate();

            tr.commit();
        } finally {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
        }
        return file;
    }

    String getTableName() {
        final Table tableAnno = this.getModelClass().getAnnotation(Table.class);
        return tableAnno != null ? tableAnno.name() : getModelClass().getName();
    }

    String getIdColumnName() {
        ClassMetadata meta = HibernateSessionFactory.getSessionFactory().getClassMetadata(getModelClass());
        return getColumnName(meta.getIdentifierPropertyName());
    }

    String getColumnName(String fieldName) {
        ClassMetadata meta = HibernateSessionFactory.getSessionFactory().getClassMetadata(getModelClass());
        if (meta instanceof AbstractEntityPersister) {
            AbstractEntityPersister persister = (AbstractEntityPersister) meta;
            return persister.getPropertyColumnNames(fieldName)[0];
        }
        return null;
    }
}