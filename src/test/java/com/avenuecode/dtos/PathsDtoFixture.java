package com.avenuecode.dtos;

import java.util.Arrays;

public class PathsDtoFixture {
    public static PathsDto newPath() {
        return PathsDto.builder()
                .path(Arrays.asList("A", "B", "C", "D"))
                .build();
    }
}