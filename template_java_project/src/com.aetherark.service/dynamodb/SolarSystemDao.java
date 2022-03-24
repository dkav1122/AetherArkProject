package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.SolarSystem;
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
}
