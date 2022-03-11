# Aether Ark Design Document

## Instructions


## *Aether Ark* Design

## 1. Problem Statement



** Aether Ark will allow users to create, update, retrieve , and delete Solar Systems of their own design. Users will create Solar Systems,
made of different celestial bodies of their chosen mass and distance from a star. Aether Ark will allows users to create custom made Solar Systems,
composed of stars, planets, and asteroids. User will then be shown an image of their solar system on the screen.



## 2. Top Questions to Resolve in Review

*List the most important questions you have about your design, or things that
you are still debating internally that you might like help working through.*


1. Whether a user will have a profile to login to Aether Ark (login page & main page: 2 pages)
2. Can users create a multiple star system?

## 3. Use Cases

*This is where we work backwards from the customer and define what our customers
would like to do (and why). You may also include use cases for yourselves, or
for the organization providing the product to customers.*

U1. As a user, I want to create a new solar system that is empty.

U2. As a user, I want to create a new celestial body with custom attributes.

U3. As a user, I want to add a celestial body to a solar system.

U4. As a user, I want to retrieve all of my personal solar systems.

U5. As a user, I want to retrieve a single solar system from all of my available solar systems

U6. As a customer, I want to update my solar systems name.

U7. As a customer, I want to update the number of planets in a solar system.

U8. As a customer, I want to update the size of a planet.

U9. As a customer, I want to destroy a solar system.

U10. As a customer, I want to destroy a planet.

U11. As a user, I want to be able to choose between making a solar system, making a planet,
or seeing all of my solar systems, or all of my planets. (user interface buttons)

U12. As a user, I want to retrieve all planets I have made.

U13. As a user, I want to be able to log in to my profile (login page)



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


# 5. Proposed Architecture Overview

*Describe broadly how you are proposing to solve for the requirements you
described in Section 2.*

*This may include class diagram(s) showing what components you are planning to
build.*

*You should argue why this architecture (organization of components) is
reasonable. That is, why it represents a good data flow and a good separation of
concerns. Where applicable, argue why this architecture satisfies the stated
requirements.*


This initial iteration will provide the minimum loveable product including creating, retrieving, updating and
destroying a custom solar system through a front-end user interface. It will also provide the ability to create, update, retrieve and destroy
individual celestial bodies, as well as add and remove them from solar systems(the update functionality)
We will use API Gateway and Lambda to create 12 endpoints (CreateSolarSystem, UpdateSolarSystem, GetSolarSystem, GetAllSolarSystems, and DestroySolarSystem and DestroyAllSolarSystems
CreateCelestialBody, UpdateCelestialBody, GetCelestialBody, GetAllCelestialBodies, DestroyCelestialBodies, DestroyAllCelestialBodies
)

We will store celestial bodies available for solar systems in a DynamoDB Table. Solar Systems themselves will also be stored in a DynamoDB Table.
For easier celestial body list retrieval, we will store the list of bodies in a given solar system directly in the solar system table.

Aether Ark will provide a web interface for users to manage their solar systems. A login page for users to login or create an account, and main page for users to choose between
creating, updating, retrieving, and deleting celestial bodies and solar systems.

# 6. API

## 6.1. Public Models
...+


Can a data model be composed of other objects? Or do the attriubutes have to be primitives and Strings?

...
###6.1.1 SolarSystemModel

String id;
String name;
List<CelestialBody> celestialBodies;
Map<String, Integer> distanceFromStar;
...


###6.1.2 CelestialBodyModel

String id;
String name;
Integer mass;
Integer diameter;
String composition;
List<String> homeSolarSystems;






## 6.0.1 *Create SolarSystem Endpoint*
- Accepts POST request to /solarsystems
- Accepts data to create a new solar system with a provided name, a given userId, and an optional list of celestial bodis
- Accepts data for celestial bodies - mass and color
- Star - temperature, Planet - life, Asteroid - has water
- Throws Exception if at least one star is not provided
- Throws exception if userId is not found



*Describe the behavior of the first endpoint you will build into your service
API. This should include what data it requires, what data it returns, and how it
will handle any known failure cases. You should also include a sequence diagram
showing how a user interaction goes from user to website to service to database,
and back. This first endpoint can serve as a template for subsequent endpoints.
(If there is a significant difference on a subsequent endpoint, review that with
your team before building it!)*

*(You should have a separate section for each of the endpoints you are expecting
to build...)*

## 6.0.2 *UpdateSolarSystem Endpoint*

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*
##6.1 CreateUser Endpoint
##6.2 GetUser Endpoint
##6.3 UpdateUser Endpoint
##6.4 DeleteUser Endpoint
## 6.5 *GetSolarSystem Endpoint*
## 6.6 *CreateCelestialBody Endpoint*
## 6.7 *UpdateCelestialBody Endpoint*
## 6.8 *GetCelestialBody Endpoint*
## 6.9 *GetAllSolarSystems Endpoint*
## 6.10 *GetAllCelestialBodies Endpoint*
## 6.11 *DestroyCelestialBody Endpoint*
## 6.12 *DestroySolarSystem Endpoint*


/api/user/{userId}/planets/{planetId}









# 7. Tables

*Define the DynamoDB tables you will need for the data your service will use. It
may be helpful to first think of what objects your service will need, then
translate that to a table structure, like with the *`Playlist` POJO* versus the
`playlists` table in the Unit 3 project.*

![Entity Relationship Diagram](C:\Users\dkav1\Backend_Development\Unit_5\aether_ark\bd-team-project-aether_ark\project_documents\Entity Relationship Diagram.puml)

entity User {
* userId: String
  --
  *name: String
  *email: String
  *solarSystemIds: List<String>
  *planetId: List<String>

}

entity SolarSystem {
* systemId: String
  --
* systemName: String
  *celestialBodies: List<celestialBody>
  *distanceFromStar: Map<String, Integer>
  }

entity CelestialBody {
*bodyId : String
--
*Type: String(Enum)
* bodyName: String
* mass: Integer
* diameter: Integer
  *homeSolarSystems: List<String>
  }


# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*