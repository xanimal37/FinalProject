# Final Project - Barter

## Members & Responsibilities
1. Edwin Flatto - Scrum Master
2. Lisa Zeeb - Repo Owner
3. Rob Tisdale - DBA

## Description
This program is designed to brigde skill gaps of individuals to allow for users to show and improve their skills in order to help those who dont have them. It is based on reciprocity to incentivise "paying it

## Instructions and expected outcomes
Users are able to login with username and password (if they dont have an account, they can register ) and be brought to their respective dashboard. Once there a user can see all upcoming tasks they have agreed to, as well as past tasks, their general rating and other pertinent information to the user. Once they navigate to the sites home page, they can engage with other users and requests tasks to be done
1. User can request a task to be done based on the skill required. Once posted, those with the pertaining skill will be notified there is a job they may be interested in. 
<!-- 2. Once accepted a task message board will open to allow for task communication -->
<!-- 3. Users can engage in general forums based on the respective skill they desire -->
4. User can edit their profile information and picture.
5. An administrator has access to a specific account which grants them overseeing privileges of all users. As well as activate and deactivate users.

***TESTING CREDENTIALS***
User
Username: 
Password: 

Admin
Username: 
Password: 
***TESTING CREDENTIALS***


## Code breakdown
### Backend
#### JPABarter - Provides initial persistence architecture and entities required<br>
Entities: and mapping<br>


#### Barter - Provides java logic classes, relationship mapping, test classes and frontend jsps
Repos, Service/Impl, Controllers<br>

### Project
| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/projects`      |              | Collection of representations of all _locations_ resources |collection** endpoint |
| GET       | `/api/projects/2`   |              | Representation of _locations_ `2` |
| POST      | `/api/projects`      | Representation of a new _location_ resource | Description of the result of the operation | **
| PUT       | `/api/projects/#`   | Representation of a new version of _location_ `#` |
| DELETE    | `/api/projects/#`   |              | |

### Location 
| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/locations`      |              | Collection of representations of all _locations_ resources |collection** endpoint |
| GET       | `/api/locations/16`   |              | Representation of _locations_ `16` |
| POST      | `/api/locations`      | Representation of a new _location_ resource | Description of the result of the operation | **
| PUT       | `/api/locations/#`   | Representation of a new version of _location_ `#` |
| DELETE    | `/api/locations/#`   |              | |

Particular code notes:

SOMETHING FOR A PARTICULAR BIT OF TRICKY CODE TO LOOK OUT FOR
```java
//controller

public void pasteNewCodeHere() {

}
```

### Frontend
Front end was broken up into several sub-directories:

## Technologies Used
1. Eclipse
2. Java / JPA / 
3. git / github
4. Spring Boot
5. Gradle
6. MySQL / MySQl Workbench
7. Angular
8. TypeScript
9. Java Script
10. CSS
11. HTML

## Lessons Learned

#### Edwin Flatto:
1. Mapping a composite key in Hibernate requires creating a separate class for the key and The @EmbeddedId annotation can be used to map the composite key in the entity class.

2. Learned how to work with a many-to-many relationship between four tables, where three of the tables are connected through a join table that has a composite key. This required understanding the use of the @ManyToMany annotation in Spring, along with the @JoinTable annotation to define the join table and its columns. It also required creating separate entities for the join table and the primary tables involved in the relationship, along with the appropriate mappings and methods to access the data.

3. When working with a four-way join table, it's important to specify all necessary join columns in the query with an And in the Jpa Repository query to find all necessary properties.

#### Lisa Zeeb
1. to use dynamic checkboxes in angular, generate from database using `*ngFor`. Two-way binding can be accomplished by creating a class called clickable, binding each checkbox property to that class (checked?:boolean) and then grabbing the objects from a list by name. This could probably be done better using an interface.

#### Rob Tisdale:
1. When doing entity mapping,for something such as comment (which can have sub-comments), you can reference the actual entity with an "inReplyTo" attribute inside the entity. 

```java
    @OneToMany(mappedBy="inReplyTo")
	private List<Comment> comments;
	
	@ManyToOne
	@JoinColumn(name="in_reply_to_id")
	private Comment inReplyTo;
	
```
In this case I am using the existing table column



