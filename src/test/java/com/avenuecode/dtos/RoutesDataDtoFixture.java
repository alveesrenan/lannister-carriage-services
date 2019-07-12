package com.avenuecode.dtos;

public class RoutesDataDtoFixture {
    public static RoutesDataDto newRoutes() {
        return RoutesDataDto.builder()
                .data(RoutesDtoFixture.newRoutesDtoFixtureList())
                .build();
    }
}