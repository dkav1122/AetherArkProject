package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.exceptions.SolarSystemNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SolarSystemDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public SolarSystemDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }


    public SolarSystem saveSolarSystem(SolarSystem solarSystem) {
        this.dynamoDBMapper.save(solarSystem);

        return solarSystem;
    }

    public SolarSystem getSolarSystem(String systemId) {
        SolarSystem solarSystem = this.dynamoDBMapper.load(SolarSystem.class, systemId);

        if(solarSystem == null) {
            throw new SolarSystemNotFoundException("Solar System Id: " + systemId + " not found.");
        }

        return solarSystem;
    }

    public SolarSystem deleteSolarSystem(SolarSystem solarSystem) {
        this.dynamoDBMapper.delete(solarSystem);

        return solarSystem;
    }

    public void deleteAllSolarSystemForUser(List<String> solarSystemId) {

        dynamoDBMapper.batchDelete(solarSystemId);

    }

    public void deleteCelestialBodyFromAllSolarSystems(CelestialBody celestialBody) {
        List<SolarSystem> solarSystems = celestialBody.getMemberSolarSystems();

        for(SolarSystem system : solarSystems) {
            system.getCelestialBodies().remove(celestialBody);
            system.getDistanceFromCenter().remove(celestialBody.getId());
            saveSolarSystem(system);
        }
    }

    public List<SolarSystem> getAllSolarSystemsForUser(String username) {
        SolarSystem solarSystem = new SolarSystem();
        solarSystem.setUsername(username);

        DynamoDBQueryExpression<SolarSystem> queryExpression = new DynamoDBQueryExpression<SolarSystem>()
                .withHashKeyValues(solarSystem)
                .withConsistentRead(false)
                .withIndexName(SolarSystem.USERNAME_INDEX);

        return new ArrayList<>(dynamoDBMapper.query(SolarSystem.class, queryExpression));

    }


}
