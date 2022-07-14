# Aether_Ark

> This service will allow a user to create their own solar system. The user can input values for each celestial body they want to create, and will be shown a visual representation of the system that they make. Celestial objects will be made separately and then added or removed from a solar system.


>This service was part of group project while enrolled at BloomTech's Backend Web Development program. 


>START HERE -> AetherArkProject/template_java_project/src/com.aetherark.service/activity


Java | Dagger | AWS Lambda | DynamoDb | AWS API Gateway | Mockito
## Features
>This initial iteration will provide the minimum viable product including creating, retrieving, updating and destroying a custom solar system through a front-end user interface. It will also provide the ability to create, update, retrieve and destroy individual celestial bodies, as well as add and remove them from solar systems(the update functionality) We will use API Gateway and Lambda to create 19 endpoints (CreateUser, GetUser, UpdateUser, DestroyUser, CreateCelestialBody, GetCelestialBody, UpdateCelestialBody, DestroyCelestialBody, DestroyAllCelestialBodies, CreateSolarSystem, GetSolarSystem, UpdateSolarSystem, DestroySolarSystem, GetAllSolarSystems, DestroyAllSolarSystems, AddACelestialBodyToSolarSystem, UpdateCelestialBodyInSolarSystem, DestroyACelestialBodyFromASolarSystem)

>We will store celestial bodies available for solar systems in a DynamoDB table. Solar systems themselves will also be stored in a DynamoDB table. User profiles will be stored in a separate DynamoDB table. For easier celestial body list retrieval, we will store the list of bodies in a given solar system directly in the solar system table.

>Aether Ark will provide a web interface for users to manage their solar systems. A login page for users to login or create an account, and main page for users to choose between creating, updating, retrieving, and deleting celestial bodies and solar systems.

