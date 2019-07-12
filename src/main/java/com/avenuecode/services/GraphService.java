package com.avenuecode.services;

import com.avenuecode.dtos.RoutesDto;
import com.avenuecode.entities.Graph;
import com.avenuecode.entities.mappers.RoutesBeanMapper;
import com.avenuecode.exceptions.GraphCreationException;
import com.avenuecode.exceptions.GraphNotFoundException;
import com.avenuecode.repositories.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraphService {

    @Autowired
    private GraphRepository repository;

    @Autowired
    private RoutesBeanMapper routesMapper;

    public Graph create(List<RoutesDto> routes) {
        return Optional.of(repository.save(buildGraph(routes)))
                .orElseThrow(() -> new GraphCreationException("An error occurred when trying to create graph."));
    }

    public Graph get(Long id) {
        return Optional.ofNullable(repository.findOne(id))
                .orElseThrow(() -> new GraphNotFoundException("There is no graph present on database"));
    }

    private Graph buildGraph(List<RoutesDto> routes) {
        return Graph.builder()
                .routes(routesMapper.fromSource(routes))
                .build();
    }
}
