package com.avenuecode.rest.dtos;

import java.util.Arrays;
import java.util.List;

public class AvailableRoutesDtoFixture {

    public static List<AvailableRoutesDto> availableRoutes() {
        return Arrays.asList(availableRouteAC1(), availableRouteAC2(), availableRouteAC3());
    }

    public static AvailableRoutesDto availableRouteAC1() {
        return AvailableRoutesDto.builder()
                .stops(2)
                .route("ABC")
                .build();
    }

    public static AvailableRoutesDto availableRouteAC2() {
        return AvailableRoutesDto.builder()
                .stops(2)
                .route("ADC")
                .build();
    }

    public static AvailableRoutesDto availableRouteAC3() {
        return AvailableRoutesDto.builder()
                .stops(3)
                .route("AEBC")
                .build();
    }

}