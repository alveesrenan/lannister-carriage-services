package com.avenuecode.dtos;

import java.util.Arrays;
import java.util.List;

public class RoutesDtoFixture {

    public static List<RoutesDto> newRoutesDtoFixtureList() {
        return Arrays.asList(
                newRoutesFixtureAB(),
                newRoutesFixtureBC(),
                newRoutesFixtureCD(),
                newRoutesFixtureDC(),
                newRoutesFixtureDE(),
                newRoutesFixtureAD(),
                newRoutesFixtureCE(),
                newRoutesFixtureEB(),
                newRoutesFixtureAE()
        );
    }

    public static RoutesDto newRoutesFixtureAB() {
        return RoutesDto.builder()
                .source("A")
                .target("B")
                .distance(5)
                .build();
    }

    public static RoutesDto newRoutesFixtureBC() {
        return RoutesDto.builder()
                .source("B")
                .target("C")
                .distance(4)
                .build();
    }

    public static RoutesDto newRoutesFixtureCD() {
        return RoutesDto.builder()
                .source("C")
                .target("D")
                .distance(8)
                .build();
    }

    public static RoutesDto newRoutesFixtureDC() {
        return RoutesDto.builder()
                .source("D")
                .target("C")
                .distance(8)
                .build();
    }

    public static RoutesDto newRoutesFixtureDE() {
        return RoutesDto.builder()
                .source("D")
                .target("E")
                .distance(6)
                .build();
    }

    public static RoutesDto newRoutesFixtureAD() {
        return RoutesDto.builder()
                .source("A")
                .target("D")
                .distance(5)
                .build();
    }

    public static RoutesDto newRoutesFixtureCE() {
        return RoutesDto.builder()
                .source("C")
                .target("E")
                .distance(2)
                .build();
    }

    public static RoutesDto newRoutesFixtureEB() {
        return RoutesDto.builder()
                .source("E")
                .target("B")
                .distance(3)
                .build();
    }

    public static RoutesDto newRoutesFixtureAE() {
        return RoutesDto.builder()
                .source("A")
                .target("E")
                .distance(7)
                .build();
    }
}