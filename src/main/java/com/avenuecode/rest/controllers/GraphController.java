package com.avenuecode.rest.controllers;

import com.avenuecode.dtos.PathsDto;
import com.avenuecode.dtos.RoutesDataDto;
import com.avenuecode.entities.mappers.GraphBeanMapper;
import com.avenuecode.mediators.GraphMediator;
import com.avenuecode.rest.dtos.AvailableRoutesDto;
import com.avenuecode.rest.dtos.GraphDto;
import com.avenuecode.rest.dtos.PathDistanceDto;
import com.avenuecode.rest.dtos.PathRouteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraphController {

    @Autowired
    private GraphBeanMapper mapper;

    @Autowired
    private GraphMediator mediator;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/graph", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GraphDto createGraph(@RequestBody RoutesDataDto data) {
        return mapper.toTarget(mediator.createGraph(data.getData()));
    }

    @GetMapping(value = "/graph/{id}")
    public GraphDto getGraph(@PathVariable Long id) {
        return mapper.toTarget(mediator.getGraph(id));
    }

    @PostMapping(value = "/routes/{graphId}/from/{town1}/to/{town2}")
    public List<AvailableRoutesDto> findAvailableRoutesBetweenTowns(@PathVariable Long graphId, @PathVariable String town1, @PathVariable String town2, @RequestParam Integer maxStops) {
        return mediator.availableRoutesBetweenTowns(graphId, town1, town2, maxStops);
    }

    @PostMapping(value = "/distance/{graphId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PathDistanceDto findDistanceForPath(@PathVariable Long graphId, @RequestBody PathsDto path) {
        return mediator.distanceForPath(graphId, path.getPath());
    }

    @PostMapping(value = "/distance/{graphId}/from/{town1}/to/{town2}")
    public PathRouteDto findShortestPathBetweenTowns(@PathVariable Long graphId, @PathVariable String town1, @PathVariable String town2) {
        return mediator.shortestPathBetweenTwoTowns(graphId, town1, town2);
    }
}
