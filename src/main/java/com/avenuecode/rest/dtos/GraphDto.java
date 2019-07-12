package com.avenuecode.rest.dtos;

import com.avenuecode.dtos.RoutesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphDto {
    private Long id;
    private List<RoutesDto> routes;
}
