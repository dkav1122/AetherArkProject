package com.aetherark.service.models;

import com.aetherark.service.dynamodb.models.SolarSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CelestialBodyModel {
    private String bodyId;
    private String name;
    private Integer diameter;
    private Integer mass;
    private String composition;
    private List<SolarSystem> memberSolarSystems;

    public CelestialBodyModel() {

    }

    public CelestialBodyModel(Builder builder) {
        this.bodyId = builder.id;
        this.name = builder.name;
        this.diameter = builder.diameter;
        this.mass = builder.mass;
        this.composition = builder.composition;
        this.memberSolarSystems = builder.memberSolarSystems;
    }

    public String getBodyId() {
        return bodyId;
    }

    public void setBodyId(String bodyId) {
        this.bodyId = bodyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public List<SolarSystem> getMemberSolarSystems() {
        return memberSolarSystems;
    }

    public void setMemberSolarSystems(List<SolarSystem> memberSolarSystems) {
        this.memberSolarSystems = memberSolarSystems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialBodyModel that = (CelestialBodyModel) o;
        return bodyId.equals(that.bodyId) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodyId, name);
    }

    @Override
    public String toString() {
        return "CelestialBodyModel{" +
                "bodyId='" + bodyId + '\'' +
                ", name='" + name + '\'' +
                ", diameter=" + diameter +
                ", mass=" + mass +
                ", composition='" + composition + '\'' +
                ", memberSolarSystems=" + memberSolarSystems +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String name;
        private Integer diameter;
        private Integer mass;
        private String composition;
        private List<SolarSystem> memberSolarSystems;

        public Builder withId(String givenId) {
            this.id = givenId;
            return this;
        }

        public Builder withName(String givenName) {
            this.name = givenName;
            return this;
        }

        public Builder withDiameter(Integer givenDiameter) {
            this.diameter = givenDiameter;
            return this;
        }

        public Builder withMass(Integer givenMass) {
            this.mass = givenMass;
            return this;
        }

        public Builder withComposition(String givenComposition) {
            this.composition = givenComposition;
            return this;
        }

        public Builder withSolarSystems(List<SolarSystem> givenSolarSystems) {
            if (givenSolarSystems == null || givenSolarSystems.isEmpty()) {
                this.memberSolarSystems = null;
            } else {
                this.memberSolarSystems = new ArrayList<>(givenSolarSystems);
            }
            return this;
        }

        public CelestialBodyModel build() {
            return new CelestialBodyModel(this);
        }
    }
}
