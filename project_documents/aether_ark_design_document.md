# Aether Ark Design Document

## *Aether Ark* Design

## 1. Problem Statement

This service will allow a user to create their own solar system. The user can input values for each celestial body they
want to create, and will be shown a visual representation of the system that they make. Celestial objects will be made 
separately and then added or removed from a solar system.


## 2. Top Questions to Resolve in Review

*List the most important questions you have about your design, or things that
you are still debating internally that you might like help working through.*

1. Do we want to use a "Soft Delete" with isDeleted? We would have to add that to the database models.
2. Can a data model be composed of other objects? Or do the attriubutes have to be primitives and Strings?
3. Should we have user validation?
4. Do we want to have a predefined set of planets?
5. Should users define initial speed and/or distance from solar body?
6. How to do the front-end part of the program?
7. How in-depth will the visuals be, and how to implement them?
8. How will we know the project is full-featured enough?
9. Whether a user will have a profile to login to Aether Ark (login page & main page: 2 pages)?
10. Can users create a multiple star system?
11. Should we have motion?
12. Do we want to connect to an outside API?
13. Should we use a DAO interface? Would it have

## 3. Use Cases

*This is where we work backwards from the customer and define what our customers
would like to do (and why). You may also include use cases for yourselves, or
for the organization providing the product to customers.*

As a Aether_Ark customer:
1. I want to create a new user account.
2. I want to log into my user account.
3. I want to update my account information.
4. I want to delete my user account (including all planets and solar systems)

5. I want to create a new solar system with custom properties(#of stars, planets with distance from star(s))
6. I want to retrieve a list all of my solar systems.
7. I want to view a single solar system from all of my available solar systems
8. I want to update an individual solar system 
    1. I want to update my solar systems name.
    2. I want to add a planet with a distance in relation to it's star(s).
    3. I want to change the distance of a planet in the solar system
    4. I want to delete a plant from the solar system. DESTROY!
    5. I want to  make changes to an indvidual planet within a solar system. (updates planet in 
   planets table and solar system?)
9. I want to delete a solar system.
11. I want to delete all of my solar systems.

12. I want to create individual custom planets.
13. I want to retrieve one planet I have made.
14. I want to retrieve all planets I have made.
15. I want to Update an individual planet (updates planet in planets table and solar system).
16. I want to Delete an individual planet (updates planet in planets table and solar system).

## 4. Project Scope

*Clarify which parts of the problem you intend to solve. It helps reviewers know
what questions to ask to make sure you are solving for what you say and stops
discussions from getting sidetracked by aspects you do not intend to handle in
your design.*

### 4.1. In Scope
-Creating, retreiving, updating and deleting a solar system

-Creating, retreiving, updating and deleting celestial bodies

-Add and remove planets to and from solar systems

- look at all planets (separate from the solar systems they belong to)
- store profiles, create log in page, show the user if they are logged in or not

### 4.2. Out of Scope

-Connecting to Nasa's API to retrieve real world information concerning actual solar systems

-Displaying a moving image of the custom solar system to the user via the front-end

-Update username if new name is available

# 5. Proposed Architecture Overview

This initial iteration will provide the minimum viable product including creating, retrieving, updating and
destroying a custom solar system through a front-end user interface. It will also provide the ability to create, 
update, retrieve and destroy
individual celestial bodies, as well as add and remove them from solar systems(the update functionality)
We will use API Gateway and Lambda to create 16 endpoints
(CreateUser, GetUser, UpdateUser, DestroyUser, 
CreateCelestialBody, GetCelestialBody, UpdateCelestialBody, DestroyCelestialBody, 
DestroyAllCelestialBodies,
CreateSolarSystem, GetSolarSystem, UpdateSolarSystem, DestroySolarSystem,
GetAllSolarSystems, DestroyAllSolarSystems)

We will store celestial bodies available for solar systems in a DynamoDB table. 
Solar systems themselves will also be stored in a DynamoDB table.
User profiles will be stored in a separate DynamoDB table
For easier celestial body list retrieval, we will store the list of bodies in a given solar system directly in the 
solar system table.

Aether Ark will provide a web interface for users to manage their solar systems. A login page for users to login or 
create an account, and main page for users to choose between
creating, updating, retrieving, and deleting celestial bodies and solar systems.

![mock-ups](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/BloomTechBackend/bd-team-project-aether_ark/project_documents/images/aether_ark_images/uml/aether_ark_CD.uml)

*You should argue why this architecture (organization of components) is
reasonable. That is, why it represents a good data flow and a good separation of
concerns. Where applicable, argue why this architecture satisfies the stated
requirements.*

# 6. API

## 6.1. Public Models
````
//UserModel
String id; 
String name; 
String email; 
List<String> solarSystemIds; 
List<String> celestialBodyIds; 
````
````
//CelestialBodyModel
String id;
String name;
Integer mass;
Integer diameter;
String composition;
List<String> homeSolarSystems;
````
````
//SolarSystemModel 
String id;
String name;
List<CelestialBody> celestialBodies;
Map<String, Integer> distanceFromStar;
````

## 6.2. GetUser Endpoint

*Describe the behavior of the first endpoint you will build into your service
API. This should include what data it requires, what data it returns, and how it
will handle any known failure cases. You should also include a sequence diagram
showing how a user interaction goes from user to website to service to database,
and back. This first endpoint can serve as a template for subsequent endpoints.
(If there is a significant difference on a subsequent endpoint, review that with
your team before building it!)*

Accepts `GET` requests to `/user/:userId`

Accepts a user ID and returns the corresponding UserModel.
If the given user ID is not found, will throw a
     `UserNotFoundException`

## 6.3 CreateUser Endpoint

Accepts `POST` requests to `/user/`

Accepts data to create a new user with a provided username and an optional email.
Returns the new user, including a unique user ID assigned by the Aether_Ark Service
Utility.
For security concerns, we will validate the provided username does not
  contain any invalid characters: `" ' \ `
If the username contains any of the invalid characters, will throw an
     `InvalidAttributeValueException`.
If the given username already exists, will throw a
  `UserNameAlreadyExistsException`

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*

##6.4 UpdateUser Endpoint
Accepts `PUT` requests to `/user/:userId`

Accepts data to update a user with a provided user ID and any changes to email.
If there are no changes, persist the data in the request.
Returns the updated User.
The username may not be updated at the moment.
For security concerns, we will validate the provided username has not changed.
If the username has changed, will throw a
`InvalidAttributeValueException`
If the given user ID is not found, will throw a
`UserNotFoundException`

##6.5 DestroyUser Endpoint
Accepts `DELETE` requests to `/user/:userId`

Accepts a user ID and deletes ALL user data from this service. 
Returns the deleted user.
Will throw a
`UserNotFoundException`

## 6.6 GetCelestialBody Endpoint
Accepts `GET` requests to `/user/:userId/celestialBody/:id`

Accepts a celestialBody ID and returns the corresponding CelestialBodyModel.
If the given celestialBody ID is not found, will throw a
`CelestialBodyNotFoundException`

## 6.7 CreateCelestialBody Endpoint
Accepts `POST` requests to `/user/:userId/celestialBody`

Accepts data to create a new celestial body with a provided name, mass, diameter,
and composition.
Returns the new celestial body, including a unique ID assigned by the Aether_Ark Service
Utility.
For security concerns, we will validate the provided celestial body does not
contain any invalid characters: `" ' \ `
If the celestial body contains any of the invalid characters, will throw an
`InvalidAttributeValueException`.
The mass and diameter must be greater than 1 otherwise, will throw an
`InvalidAttributeValueException`
![mock-ups](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/BloomTechBackend/bd-team-project-aether_ark/project_documents/images/aether_ark_images/uml/create_celestial_body_SD.uml)

## 6.8 UpdateCelestialBody Endpoint
Accepts `PUT` requests to `/user/:userId/celestialBody/:id`

will throw a
`CelestialBodyNotFoundException`

## 6.9 DestroyCelestialBody Endpoint
Accepts `DELETE` requests to `/user/:userId/celestialBody/:id`

will throw a
`CelestialBodyNotFoundException`

## 6.10 GetAllCelestialBodies Endpoint
Accepts `GET` requests to `/user/:userId/celestialBody/`

will throw a
`CelestialBodyNotFoundException`

## 6.11 DestroyAllCelestialBodies Endpoint
Accepts `DELETE` requests to `/user/:userId/celestialBody`

## 6.12 GetSolarSystem Endpoint
Accepts `GET` requests to `/user/:userId/solarSystem/:solarSystemId`

Accepts a solar system ID and returns the corresponding solar system model.
If the given solar system ID is not found, will throw a
`SolarSystemNotFoundException`
## 6.13 CreateSolarSystem Endpoint
Accepts `POST` requests to `/user/:userId/solarSystem/`
## 6.14 UpdateSolarSystem Endpoint
Accepts `PUT` requests to `/user/:userId/solarSystem/:solarSystemId`
## 6.15 DestroySolarSystem Endpoint
Accepts `DELETE` requests to `/user/:userId/solarSystem/:solarSystemId`
## 6.16 GetAllSolarSystems Endpoint
Accepts `GET` requests to `/user/:userId/solarSystem/`
## 6.17 DestroyAllSolarSystems Endpoint
Accepts `DELETE` requests to `/user/:userId/solarSystem/`

# 7. Tables

![Tables](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/BloomTechBackend/bd-team-project-aether_ark/project_documents/images/aether_ark_images/uml/aether_ark_tables_ERD.uml)


# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*

![mock-ups.](images/aether_ark_images/web_page_mock_1st.png)

