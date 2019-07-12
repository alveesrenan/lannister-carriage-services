package com.avenuecode.entities;

public class GraphFixture {

    public static Graph newGraphFixture() {
        return Graph.builder()
                .id(1L)
                .routes(RoutesFixture.newRoutesFixtureList())
                .build();
    }
}