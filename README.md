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

There are a lot of intertwining REST end point. The following are the most prominent of the project

### User
| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/users`      |              | Collection of all users|
| GET       | `/api/users/{id}`   |   Representation of a userId resource    | Single user based on Id |
| POST      | `/api/register`     | Representation of a new User | Creates new user | 
| PUT       | `/api/user/{userId}`   | User object, Id | Updated User based on id
| DELETE    | `/api/locations/#`   |      ***FINISHc       

### Task 
| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/tasks`      |              | Collection of all tasks in the database |
| GET       | `/api/users/tasks`   |      Principal        | Collection of all tasks for logged in users |
| GET       | `/api/tasks/{statusname}`   |      Principal        | Collection of all tasks for based on status |
| POST      | `/api/tasks`      | Principal, Representation of an new Task| New Task Object 
| PUT       | `/api/tasks/{id}`   | Principal, Representation of an new Task and {id}| Updated Task based on id

### Post 
| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/posts`      |              | Collection of all posts in the database |
| GET       | `/api/users/{Id}`   |      Principal        | Collection of all posts for user |
| GET       | `/api/search/{keyword}`   |      Principal, keyword string        | Search results based on keyword |
| POST      | `/api/post`      | Principal, Representation of an new Post| New Task Object 
| PUT       | `/api/tasks/{id}`   | Principal, Representation of an new Post and {id}| Updated Post based on id
| PUT       | `/api/disabled/{id}`   | Principal, Post Id| Turns Comment to false for archive purposes. 



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

2. Reactive forms allow for modifying data before being added to a new object. For instance, it is easier to create dates because they can be formatted differently from plain HTML values. Also dynamically generating forms and getting input is more flexible.

3. Angular function calls are made on every update. So an `*ngIF` that has a function can get called 300+ times just on loading a component. Avoid by using property.

4. @Output, @Input for communication between parent/child components. More efficient than functions, which get called on every update.

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



