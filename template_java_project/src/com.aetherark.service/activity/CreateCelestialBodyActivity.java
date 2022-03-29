package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.CelestialBodyDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.CelestialBodyModel;
import com.aetherark.service.models.requests.CreateCelestialBodyRequest;
import com.aetherark.service.models.results.CreateCelestialBodyResult;
import com.aetherark.service.util.AetherArkServiceUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class CreateCelestialBodyActivity
        implements RequestHandler<CreateCelestialBodyRequest, CreateCelestialBodyResult> {
    private final CelestialBodyDao celestialBodyDao;
    private final UserDao userDao;

    @Inject
    public CreateCelestialBodyActivity(CelestialBodyDao celestialBodydao, UserDao userDao) {
        this.celestialBodyDao = celestialBodydao;
        this.userDao = userDao;
    }

    @Override
    public CreateCelestialBodyResult handleRequest(
            final CreateCelestialBodyRequest createCelestialBodyRequest, Context context) {

//        TODO: add logging?

        try {
            User user = userDao.getUser(createCelestialBodyRequest.getUsername());
        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException(exception.getMessage());
        }

        if (!AetherArkServiceUtils.isValidString(createCelestialBodyRequest.getName())) {
            throw new InvalidAttributeException(String.format(
                    "The provided name: %s contains invalid characters", createCelestialBodyRequest.getName()));
        }

        CelestialBody newCelestialBody = new CelestialBody();
        newCelestialBody.setId(AetherArkServiceUtils.generateId()); // is ID definitely unique?
        newCelestialBody.setName(createCelestialBodyRequest.getName());
        newCelestialBody.setUsername(createCelestialBodyRequest.getUsername());
        newCelestialBody.setDiameter(createCelestialBodyRequest.getDiameter());
        newCelestialBody.setMass(createCelestialBodyRequest.getMass());
        newCelestialBody.setComposition(createCelestialBodyRequest.getComposition());
        // Not a member of any SolarSystems
        newCelestialBody.setMemberSolarSystems(new ArrayList<>());

        celestialBodyDao.saveCelestialBody(newCelestialBody);
        userDao.addToUserCelestialBodyId(newCelestialBody.getUsername(), newCelestialBody.getId());

        CelestialBodyModel celestialBodyModel = new ModelConverter().toCelestialBodyModel(newCelestialBody);
        return CreateCelestialBodyResult.builder()
                .withCelestialBody(celestialBodyModel)
                .build();
    }
}
