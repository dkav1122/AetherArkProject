package com.aetherark.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;


@DynamoDBTable(tableName = "celestial_bodies")
public class CelestialBody {

    private String id;
    private String name;
    private String username;
    private Integer diameter;
    private Integer mass;
    private String composition;
    private List<SolarSystem> memberSolarSystems;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "diameter")
    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    @DynamoDBAttribute(attributeName = "mass")
    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    @DynamoDBAttribute(attributeName = "composition")
    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    @DynamoDBAttribute(attributeName = "solar_systems")
    public List<SolarSystem> getMemberSolarSystems() {
        return memberSolarSystems;
    }

    public void setMemberSolarSystems(List<SolarSystem> memberSolarSystems) {
        this.memberSolarSystems = memberSolarSystems;
    }
}
