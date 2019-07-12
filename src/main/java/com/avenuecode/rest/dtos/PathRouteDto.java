package com.avenuecode.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PathRouteDto {
    private int distance;
    private List<String> path;
    private boolean found;

    public PathRouteDto() {
        this.distance = 0;
        this.found = false;
        this.path = new ArrayList<>();
    }

    public static PathRouteDto emptyTownsDistance() {
        return PathRouteDto.builder()
                .distance(0)
                .build();
    }

    public static PathRouteDto nonExistentTownsDistance() {
        return PathRouteDto.builder()
                .distance(-1)
                .build();
    }
}
