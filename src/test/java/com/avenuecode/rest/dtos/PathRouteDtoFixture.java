package com.avenuecode.rest.dtos;

import java.util.Arrays;
import java.util.List;

public class PathRouteDtoFixture {

    public static List<PathRouteDto> pathRoutes2Stops() {
        return Arrays.asList(routeAC1(), routeAC2());
    }

    public static List<PathRouteDto> pathRoutes3Stops() {
        return Arrays.asList(routeAC1(), routeAC2(), routeAC3());
    }

    public static PathRouteDto routeAC1() {
        return PathRouteDto.builder()
                .distance(9)
                .path(Arrays.asList("A", "B", "C"))
                .found(true)
                .build();
    }

    public static PathRouteDto routeAC2() {
        return PathRouteDto.builder()
                .distance(13)
                .path(Arrays.asList("A", "D", "C"))
                .found(true)
                .build();
    }

    public static PathRouteDto routeAC3() {
        return PathRouteDto.builder()
                .distance(14)
                .path(Arrays.asList("A", "E", "B", "C"))
                .found(true)
                .build();
    }
}