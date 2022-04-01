# [Aether Ark] Project Rubric

## Background

*This captures the expectations that we have for your team during the unit.
This is how we will evaluate whether you have demonstrated these expectations.*

## Instructions

*As a team, complete this rubric (everything except for the Appendix) by
answering the questions below. Each question should usually only require one or
two sentences, so please be brief. The remainder of expectations will be
evaluated by instructors, so you don’t need to write anything in the Appendix.
We want you to see the full set of expectations for transparency’s sake.*

## Deliverables

*Provide links to the following project deliverables:*

|Deliverable                                                      |Due Date                  |Date Completed |URL                               |
|---                                                              |---                       |---            |---                               |
|Team name                                                        |Sprint 1 Module 1         | 3/7/2022      |name: Aether_Ark                  |
|[Design Document - problem statement](design_document.md)        |Sprint 1 Module 2         | 3/7/2022      |https://github.com/BloomTechBackend/bd-team-project-aether_ark/blob/main/project_documents/aether_ark_design_document.md|
|[Team Charter](team_charter.md)                                  |Sprint 1 Module 3         | 3/9/2022      |https://github.com/BloomTechBackend/bd-team-project-aether_ark/blob/main/project_documents/team_charter.md|
|[Design Document](design_document.md)                            |Sprint 1 Friday by 5pm    | 3/11/2022     |https://github.com/BloomTechBackend/bd-team-project-aether_ark/blob/main/project_documents/aether_ark_design_document.md|
|Project Completion (Feature Complete)                            |Sprint 3 Friday by 5pm    |               |                                  |
|[Team Reflection](reflection.md)                                 |Sprint 4 Wednesday by 5PM |               |                                  |
|[Accomplishment Tracking (person 1)](accomplishment_tracking.md) |Sprint 4 Wednesday by 5PM |               |                                  |
|[Accomplishment Tracking (person 2)](accomplishment_tracking.md) |Sprint 4 Wednesday by 5PM |               |                                  |
|[Accomplishment Tracking (person 3)](accomplishment_tracking.md) |Sprint 4 Wednesday by 5PM |               |                                  |
|[Accomplishment Tracking (person 4)](accomplishment_tracking.md) |Sprint 4 Wednesday by 5PM |               |                                  |
|Self Reflection (person 1)                        |Sprint 4 Wednesday by 5PM |               |n/a (will be submitted via Canvas - "Wrap-up" section) |
|Self Reflection (person 2)                        |Sprint 4 Wednesday by 5PM |               |n/a (will be submitted via Canvas - "Wrap-up" section) |
|Self Reflection (person 3)                        |Sprint 4 Wednesday by 5PM |               |n/a (will be submitted via Canvas - "Wrap-up" section) |
|Self Reflection (person 4)                        |Sprint 4 Wednesday by 5PM |               |n/a (will be submitted via Canvas - "Wrap-up" section) |

## Technical Learning Objectives

### API Design

**Design an API to meet the needs of your application.** Provide a link to the
definition for your endpoints (can be code/configuration, or can be
documentation). List one thing that your team learned about designing a good
API.

*Endpoint definition location:* https://aether-ark-api-def.s3.us-west-2.amazonaws.com/index.html   
*What we learned:*  We learned the importance of marking the difference between a request that has data in the 
                    query and data that belongs in the request body. 

**Develop a service endpoint definition that uses complex inputs and outputs.**
Select one of your endpoints and list the operation’s input and output objects.
Under each, list its attributes.

*Endpoint: /user     
*Input object(s):Username & Email Request      

* username: string
* email: string

*Output object(s): 201 Created User Object     

* username : string
* email: string
* solarSystemIds: array[ string ]
* celestialBodyIds: array [ string ]

*Output object: 400 Bad Request

* code: 0
* type: string
* message: string

**Given a user story that requires a user to provide values to a service
endpoint, design a service endpoint including inputs, outputs, and errors.**
Select one of your endpoints that accepts input values from a client. List the
error cases you identified and how the service responds in each case. Provide at
most 3 error cases.

|**Endpoint:**  |       /user/{username}              |
|---            |---                  |
|**Error case** |**Service response** |
| Bad Request   | 400 Error Message  Invalid Characters                  |
|  Not Found             |   404 Error Message User does not Exist                |
|  Bad Request          |  400 Error Message Invalid email                    |

**Develop a service endpoint definition that uses query parameters to determine
how results are sorted or filtered.** List at least one endpoint that allows
sorting or filtering of results. Which attribute(s) can be sorted/filtered on?

*Endpoint:/user/{username}/solarSystem/{solarSystemId       
*Attribute(s): getAll: boolean (returns all solar systems for the user)  

**Determine whether a given error condition should result in a client or server
exception.** List one client exception and one server exception that your
service code throws. List one condition in which this exception is thrown.

|                       |**Exception** |**One case in which it is thrown** |
|---	                |---	       |---	                               |
|**Client exception:**  | SolarSystemNotFoundException	           | User provides invalid solar system id	                               |
|**Service exception:** | Internal Server Error	           |	DynamoDB could not process request                               |

### DynamoDB Table Design

**Decompose a given set of use cases into a set of DynamoDB tables that provides
efficient data access.** List the DynamoDB tables in your project:

1.  Users
2.  SolarSystems
3. CelestialBody


**Design a DynamoDB table key schema that allows items to be uniquely
identified.** Describe the primary key structure for your DynamoDB table with
the most interesting primary key. In a sentence or two, explain your choice of
partition/sort key(s).

1. Solar System Table uses a generated solar system Id to act as the partition key. Using a partition
    key only is a simple way to enforce secuirty for Users to only add planets to solar systems that they own.

**Design the attributes of a DynamoDB table given a set of use cases.** List a
DynamoDB table with at least 3 attributes. List one relevant use case that uses
the attribute. In one sentence, describe why the attribute is included.

**Table name:SolarSystems  
 
**Attributes:**

|Attribute name |(One) relevant use case |attribute purpose |
|---            |---                     |---               |
|   solarSystemId            |  User wants to get a solar system| solarSystemId acts as the partition key |
|   celestialBodies            | User wants to Remove a planet from a solar system| CelestialBodies holds a list of all of a solar systems member celestial bodies                  |
|   distanceFromCenter  | User wants to change how far a planet is from the center of the solar system| A map where planetId's is the key and distance from center(integer) is the value that can be updated                  |
|   SystemName            | User wants to change the name of their solar system | String value representing name of solar system                  |
|   UserName            | User wants to see all of their solar systems                        | Check solar systems to see if solar system's username matches the User making the getAll request                  |

### DynamoDB Indexes

**Design a GSI key schema and attribute projection that optimizes queries not
supported by a provided DynamoDB table.** In one or two sentences, explain why
you created one of the GSIs that your project uses, including one use case that
uses that index.

**Table name: SolarSystems

**Table keys: solarSystemId

**GSI keys: username

**Use case for GSI: User wants to retrieve all of their solar systems. 

**Implement functionality that uses query() to retrieve items from a provided
DynamoDB's GSI.** List one of your use cases that uses `query()` on a GSI.

**Table name: SolarSystems (GSI = GetAllSolarSystemsForUser)

**Use case for `query()` on GSI:** User provides getAll = true, and instead of retrieving a single solar system
                                    by the solarSystemId, they now retrieve all of their solar systems using their username as
                                    the partition key on the GSI.

## Soft(er) Outcomes

**Learn a new technology.** For each team member, list something new that that
team member learned:

|Team Member |Something new the team member learned |   
|---   |---                                   |
|      |                                      |   
|      |                                      |     
|      |                                      |     
|      |                                      |     

**Identify key words to research to accomplish a technical goal | Use sources
like sage and stack overflow to solve issues encountered while programming.**
Give an example of a search term that your team might have used to find an
answer to a technical question/obstacle that your team ran into. List the
resource that you found that was helpful.

**Search terms:**      
**Helpful resource:**      

**Find material online to learn new technical topics.** List one resource that
your team found on your own that helped you on your project.

**Topic:**

**Resource:**

**Share what was worked on yesterday, the plan for today, and any blockers as a
part of a scrum standup.** In one or two sentences, describe what your team
found to be the most useful outcome from holding daily standups.

1.

