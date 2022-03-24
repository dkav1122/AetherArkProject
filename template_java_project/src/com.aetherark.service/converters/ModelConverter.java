package com.aetherark.service.converters;

import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.models.CelestialBodyModel;

public class ModelConverter {

    /**
     * Converts a provided {@link CelestialBody} into a {@link CelestialBodyModel} representation.
     * @param body the Celestial Body to convert
     * @return the converted model
     */
    public CelestialBodyModel toCelestialBodyModel(CelestialBody body) {
        return CelestialBodyModel.builder()
                .withId(body.getId())
                .withName(body.getName())
                .withDiameter(body.getDiameter())
                .withMass(body.getMass())
                .withComposition(body.getComposition())
                .withSolarSystems(body.getMemberSolarSystems())
                .build();
    }



}
