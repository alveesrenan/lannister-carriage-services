package com.avenuecode.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PathDistanceDto {
    private int distance;

    public static PathDistanceDto emptyPathDistance() {
        return PathDistanceDto.builder()
                .distance(0)
                .build();
    }
}
