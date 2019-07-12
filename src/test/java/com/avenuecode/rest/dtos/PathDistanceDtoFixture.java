package com.avenuecode.rest.dtos;

public class PathDistanceDtoFixture {

    public static PathDistanceDto pathDistance() {
        return PathDistanceDto.builder()
                .distance(3)
                .build();
    }
}