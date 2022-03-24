package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.exceptions.SolarSystemNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

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
}
