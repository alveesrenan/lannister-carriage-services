package com.avenuecode.services;

import com.avenuecode.entities.Graph;
import com.avenuecode.entities.Routes;
import com.avenuecode.exceptions.NoSuchRouteException;
import com.avenuecode.rest.dtos.PathRouteDto;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@Log4j
public class PathService {

    public PathRouteDto shortestPath(Graph graph, String town1, String town2) {
        if (town1.contentEquals(town2)) {
            log.error("There is no way to calculate distance between the same town!");
            return PathRouteDto.emptyTownsDistance();
        }

        log.info(format("Trying to find the shortest path between town '%s' and '%s'", town1, town2));
        List<PathRouteDto> pathRoutes = createWholeRoutesForPath(graph, town1, town2);
        if (pathRoutes.isEmpty()) {
            log.error(format("There is no path between town '%s' and town '%s'", town1, town2));
            return PathRouteDto.nonExistentTownsDistance();
        }

        return findShortestPathFromPath(pathRoutes);
    }

    public List<PathRouteDto> createWholeRoutesForPath(Graph graph, String town1, String town2) {
        List<PathRouteDto> paths = new ArrayList<>();
        List<Routes> sourceRoutes = filterRoutesBySource(town1, graph.getRoutes());

        sourceRoutes.forEach(route -> {
            PathRouteDto path = new PathRouteDto();
            if (route.getSource().contentEquals(town1) && route.getTarget().contentEquals(town2)) {
                log.info("Shortest path is already its connection!");
                buildSameTownDistanceDto(route, path, paths);
                return;
            }

            log.info("Starting shortest path approach process...");
            fillDistanceAndPath(path, route.getDistance(), route.getSource());
            getRoutesByTown(graph, route, town1, town2, path);

            paths.add(path);
        });
        return paths;
    }

    private void getRoutesByTown(Graph graph, Routes sourceRoute, String source, String target, PathRouteDto path) {
        Routes routeAuxIndex = null;
        List<Routes> linkedRoutes = filterRoutesLinked(sourceRoute, graph.getRoutes());

        for (Routes linkedRoute : linkedRoutes) {
            if (!path.isFound() && !linkedRoute.getTarget().contentEquals(source)) {
                routeAuxIndex = linkedRoute;
                fillDistanceAndPath(path, linkedRoute.getDistance(), linkedRoute.getSource());
                if (linkedRoute.getTarget().contentEquals(target)) {
                    path.setFound(true);
                    path.getPath().add(linkedRoute.getTarget());
                }
            }
        }

        if (!path.isFound()) {
            int index = linkedRoutes.indexOf(routeAuxIndex);
            getRoutesByTown(graph, linkedRoutes.get(index), source, target, path);
        }
    }

    private PathRouteDto findShortestPathFromPath(List<PathRouteDto> pathRoutes) {
        return pathRoutes.stream()
                .min(Comparator.comparing(PathRouteDto::getDistance))
                .orElseThrow(() -> new NoSuchRouteException("NO SUCH ROUTE"));
    }

    private List<Routes> filterRoutesLinked(Routes sourceRoute, List<Routes> routes) {
        return routes.stream()
                .filter(target -> target.getSource().contentEquals(sourceRoute.getTarget()))
                .collect(Collectors.toList());
    }

    private List<Routes> filterRoutesBySource(String source, List<Routes> routes) {
        return routes.stream()
                .filter(route -> route.getSource().contentEquals(source))
                .collect(Collectors.toList());
    }

    private void fillDistanceAndPath(PathRouteDto pathDto, int distance, String path) {
        pathDto.setDistance(pathDto.getDistance() + distance);
        pathDto.getPath().add(path);
    }

    private void buildSameTownDistanceDto(Routes route, PathRouteDto path, List<PathRouteDto> paths) {
        path.setFound(true);
        path.setDistance(route.getDistance());
        path.getPath().addAll(Arrays.asList(route.getSource(), route.getTarget()));

        paths.add(path);
    }
}
