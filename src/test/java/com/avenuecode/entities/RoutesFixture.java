package com.avenuecode.entities;

import java.util.Arrays;
import java.util.List;

public class RoutesFixture {

    public static List<Routes> newRoutesFixtureList() {
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

    public static Routes newRoutesFixtureAB() {
        return Routes.builder()
                .source("A")
                .target("B")
                .distance(5)
                .build();
    }

    public static Routes newRoutesFixtureBC() {
        return Routes.builder()
                .source("B")
                .target("C")
                .distance(4)
                .build();
    }

    public static Routes newRoutesFixtureCD() {
        return Routes.builder()
                .source("C")
                .target("D")
                .distance(8)
                .build();
    }

    public static Routes newRoutesFixtureDC() {
        return Routes.builder()
                .source("D")
                .target("C")
                .distance(8)
                .build();
    }

    public static Routes newRoutesFixtureDE() {
        return Routes.builder()
                .source("D")
                .target("E")
                .distance(6)
                .build();
    }

    public static Routes newRoutesFixtureAD() {
        return Routes.builder()
                .source("A")
                .target("D")
                .distance(5)
                .build();
    }

    public static Routes newRoutesFixtureCE() {
        return Routes.builder()
                .source("C")
                .target("E")
                .distance(2)
                .build();
    }

    public static Routes newRoutesFixtureEB() {
        return Routes.builder()
                .source("E")
                .target("B")
                .distance(3)
                .build();
    }

    public static Routes newRoutesFixtureAE() {
        return Routes.builder()
                .source("A")
                .target("E")
                .distance(7)
                .build();
    }

}