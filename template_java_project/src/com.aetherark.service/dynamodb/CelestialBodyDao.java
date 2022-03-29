package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.exceptions.CelestialBodyNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

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

    /**
     * Deletes the {@link CelestialBody} from the DynamoDB table.
     *
     * @param body the Celestial Body to be deleted
     * @return the deleted Celestial Body
     */
    public CelestialBody deleteCelestialBody(CelestialBody body) {
        this.dynamoDbMapper.delete(body);
        return body;
    }

    /**
     * Adds the provided {@link SolarSystem} to the list of SolarSystems of which this {@link CelestialBody} is a member.
     *
     * @param bodyId the ID of the Celestial Body which has been added to the Solar System
     * @param solarSystem the Solar System that now contains the Celestial Body
     * @return the updated Celestial Body object
     */
    public CelestialBody addToBodyMemberSolarSystems(String bodyId, SolarSystem solarSystem) {
        CelestialBody body = getCelestialBody(bodyId);
        List<SolarSystem> solarSystemList = body.getMemberSolarSystems();
        solarSystemList.add(solarSystem);
        body.setMemberSolarSystems(solarSystemList);
        return saveCelestialBody(body);
    }

    /**
     * Removes the provided {@link SolarSystem} from the list of SolarSystems of which this {@link CelestialBody} is a member.
     *
     * @param bodyId the ID of the Celestial Body which has been removed from the Solar System
     * @param solarSystem the Solar System that no longer contains the Celestial Body
     * @return the updated Celestial Body object
     */
    public CelestialBody removeFromBodyMemberSolarSystems(String bodyId, SolarSystem solarSystem) {
        CelestialBody body = getCelestialBody(bodyId);
        List<SolarSystem> solarSystemList = body.getMemberSolarSystems();
        solarSystemList.remove(solarSystem);
        body.setMemberSolarSystems(solarSystemList);
        return saveCelestialBody(body);
    }

    /**
     * Deletes every {@link CelestialBody} with a provided id.
     *
     * @param bodyIds the List of ids to be deleted
     */
    public void deleteCelestialBodiesList(List<String> bodyIds) {
        dynamoDbMapper.batchDelete(bodyIds);
    }
}
