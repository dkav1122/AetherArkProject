package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.exceptions.CelestialBodyNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
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
     * @return the Celestial Body, or throws {@link CelestialBodyNotFoundException} if given id is not found
     */
    public CelestialBody getCelestialBody(String id) {
        CelestialBody body = this.dynamoDbMapper.load(CelestialBody.class, id);

        if (body == null) {
            throw new CelestialBodyNotFoundException("Could not find planet with id " + id);
        }

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
