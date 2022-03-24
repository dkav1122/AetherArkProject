package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.CelestialBody;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;


public class CelestialBodyDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a CelestialBodyDao Object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the CelestialBody table
     */
    @Inject
    public CelestialBodyDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link CelestialBody} corresponding to the provided id.
     *
     * @param id the CelestialBody ID
     * @return the Celestial Body, or null if none is found
     */
    public CelestialBody getCelestialBody(String id) {
        CelestialBody body = this.dynamoDbMapper.load(CelestialBody.class, id);

        //TODO: Implement exceptions/errors
//        if (body == null) {
//
//        }

        return body;
    }

    /**
     * Saves the provided {@link CelestialBody} object to the DynamoDB table.
     *
     * @param body the Celestial Body that will be saved
     * @return the Celestial Body that was saved
     */
    public CelestialBody saveCelestialBody(CelestialBody body) {
        dynamoDbMapper.save(body);
        return body;
    }


}
