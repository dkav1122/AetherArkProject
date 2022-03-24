package com.aetherark.service.activity;

import com.aetherark.service.dynamodb.SolarSystemDao;
import com.aetherark.service.models.requests.GetSolarSystemRequest;
import com.aetherark.service.models.results.GetSolarSystemResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;

public class GetSolarSystemActivity implements RequestHandler<GetSolarSystemRequest, GetSolarSystemResult> {
    private final SolarSystemDao solarSystemDao;


    @Inject
    public GetSolarSystemActivity(SolarSystemDao solarSystemDao) {
        this.solarSystemDao = solarSystemDao;
    }

    @Override
    public GetSolarSystemResult handleRequest(final GetSolarSystemRequest getSolarSystemRequest, Context context) {

        return GetSolarSystemResult;
    }


}
