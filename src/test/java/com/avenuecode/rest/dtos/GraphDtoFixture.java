package com.avenuecode.rest.dtos;

import com.avenuecode.dtos.RoutesDtoFixture;

public class GraphDtoFixture {

    public static GraphDto newGraphDtoFixture() {
        return GraphDto.builder()
                .id(1L)
                .routes(RoutesDtoFixture.newRoutesDtoFixtureList())
                .build();
    }
}