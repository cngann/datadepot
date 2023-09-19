# Depot
What is this?  It's a multi-purpose DBI tool for accessing our databases from anywhere we want.
It can be compiled as both a web service and included as a `jar`, depending on what the needs of the application are
and what the endpoint is.

Currently, it is using MariaDB for the connector, but can be easily adapted to any database.  Example hibernate configs 
have been created to demonstrate how that happens.
 
### Notes
Take a look at `Admin.class::main()` for notes on the un-parameterized `ArrayLists()` and
backwards compatibility with DBI1

Currently, I am only working with v800+ DB's, but will be implementing legacy tables soon

#### ToDo
* Instructions on how to do joins and multi-dimensional queries
* Decide if we want to do IP whitelisting, or just stick with API keys

##### You'll need to add the following to the DB (any version)
```
CREATE TABLE `api_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_key` varchar(32) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

CREATE TABLE entity_event (
    entity_event_id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,  
    entity_type_id INT(11) NOT NULL,
    entity_id INT(11) NOT NULL,
    application VARCHAR(100),
    system_login_id INT(11), 
    event VARCHAR(50) NOT NULL,  
    date timestamp NOT NULL
);  

CREATE TABLE entity_status (
    entity_status_id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    entity_type_id INT(11) NOT NULL,
    entity_id INT(11) NOT NULL,
    state VARCHAR(10) NOT NULL,
    create_system_login_id INT(11),
    create_date timestamp NOT NULL,
    update_system_login_id INT(11),
    update_date timestamp
);

CREATE TABLE entity_type (
    entity_type_id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    type VARCHAR(100) NOT NULL,
    record_events boolean NOT NULL,
    record_status boolean NOT NULL,
    logical_delete_flag boolean NOT NULL
);
```

### How does this work?
#### Reources
Resources are the actual functional business logic classes associate with a table or payload.
They contain custom methods for data retrieval and storage that are related to the resource, which generally
align with tables, external data sources, or combinations of both. They directly call an associated data class containing the actual data structure
for the table, payload, or combined datasource.
##### Types of resources:
* Table
* Payload
* External

##### Table Resource
* Contains all available requests for each table, methods are build to take a REST parameter or a regular method call if used as a jar
* Houses the raw datatype classes which define the relationships between table column names and Jame field names, also specifies primary keys, 
sequence (`AUTO_INCREMENT`) fields, foreign key relationships, and othe table properties.  Also defines whether the table and its subsequences 
accessors are subjec to the Entity/Event logging system
##### Payload Resource
When a combination of objects needs to be returned, a payload object should be created.  It is essentially a data class that combines other data classes together to create a data payload.
For example, if you want to look up an admin user and also retrieve their admin permissions, you would create a wrapper object with Admin and AdminPriv objects inside of it.
##### Extenal Resource
Not fleshed out yet, but would act as an interface for speaking with other applications using the same data class format, but with the ability to specify a non-database endpoint.
##### Filter Resource
Filter resources contain higher-level resources that are only used when depot is running as a Web application and
extend existing Tomcat classes.  They would need to be changed/adapted to use other containers such as Glassfish, jetty, etc.

The basic structure is:
```
AbstractResource
    +-- get/getAll
    +-- update
    +-- add/addALl
    +-- remove/removeAll
    +-- ...()
        +-- AdminResource
            +-- getByUser()
            +-- getByWhatever()
            +-- updateByName()
            +-- ...()
                +-- Admin
                    +-- id
                    +-- userId
                    +-- ...
```



#### Payload Resource Example
```java
@Path("adminadminprivpayload")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminAdminPriv {
    @Context
    private ResourceContext _resourceContext;

    public AdminAdminPriv() { }
    private Admin getAdmin(String id) {
        return _resourceContext.getResource(AdminResource.class).getByUser(id);
    }

    private List<AdminPriv> getAdminPriv(Long id) {
        return _resourceContext.getResource(AdminPrivResource.class).getByUser(id);
    }

    @GET
    public AdminAdminPrivPayload getAdminAdminPrivPayload(@QueryParam("id") String userId) {
        final AdminAdminPrivPayload adminAdminPrivPayload = new AdminAdminPrivPayload();
        adminAdminPrivPayload.admin = getAdmin(userId);
        adminAdminPrivPayload.adminPrivs = getAdminPriv(adminAdminPrivPayload.admin.getId());
        return adminAdminPrivPayload;
    }

    @XmlRootElement
    public static class AdminAdminPrivPayload {
        public AdminAdminPrivPayload() { };
        @XmlElement(name="admin")
        private Admin admin;
        @XmlElementWrapper(name="admin")
        private List<AdminPriv> adminPrivs;
    }
}
```
Would return an object like this:
```javascript
{
  "admin": [
    {
      "id": "22",
      "userName": "elliscr"
    },
    {
      "adminPrivs": [
        {
          "adminPriv": "Read-Only",
          "id": "147",
          "partitionId": "2",
          "userId": "22"
        },
        {
          "adminPriv": "Read-Only",
          "id": "148",
          "partitionId": "3",
          "userId": "22"
        },
        {
          "adminPriv": "Read-Only",
          "id": "149",
          "partitionId": "4",
          "userId": "22"
        }
      ]
    }
  ]
}
```
