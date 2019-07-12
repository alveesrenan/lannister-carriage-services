package com.avenuecode.mediators;

import com.avenuecode.dtos.RoutesDto;
import com.avenuecode.entities.Graph;
import com.avenuecode.rest.dtos.AvailableRoutesDto;
import com.avenuecode.rest.dtos.PathDistanceDto;
import com.avenuecode.rest.dtos.PathRouteDto;
import com.avenuecode.services.GraphService;
import com.avenuecode.services.PathService;
import com.avenuecode.services.RoutesService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
@Log4j
public class GraphMediator {

    @Autowired
    private GraphService graphService;

    @Autowired
    private RoutesService routesService;

    @Autowired
    private PathService pathService;

    public Graph createGraph(List<RoutesDto> routes) {
        log.info("Creating new graph according its routes on database...");
        return graphService.create(routes);
    }

    public Graph getGraph(Long id) {
        log.info(format("Getting graph according id '%s'", id));
        return graphService.get(id);
    }

    public List<AvailableRoutesDto> availableRoutesBetweenTowns(Long graphId, String town1, String town2, Integer maxStops) {
        log.debug(format("Getting graph according id '%s'", graphId));
        Graph graph = graphService.get(graphId);

        log.info(format("Getting available routes from '%s' to '%s' with max stops '%s'", town1, town2, maxStops));
        return routesService.availableRoutes(graph, town1, town2, maxStops);
    }

    public PathDistanceDto distanceForPath(Long graphId, List<String> towns) {
        if (towns.isEmpty() || towns.size() == 1) {
            log.error("There is no possibility to find distance with towns provided!");
            return PathDistanceDto.emptyPathDistance();
        }

        log.debug(format("Getting graph according id '%s'", graphId));
        Graph graph = graphService.get(graphId);

        log.info("Calculating distance between towns...");
        return routesService.sumDistanceForPath(graph, towns);
    }

    public PathRouteDto shortestPathBetweenTwoTowns(Long graphId, String town1, String town2) {
        if (town1.contentEquals(town2)) {
            log.error("There is no possibility to calculate the shortest path between the same towns!");
            return PathRouteDto.emptyTownsDistance();
        }

        log.debug(format("Getting graph according id '%s'", graphId));
        Graph graph = graphService.get(graphId);
        return pathService.shortestPath(graph, town1, town2);
    }
}
