package com.avenuecode.services;

import com.avenuecode.entities.Graph;
import com.avenuecode.entities.Routes;
import com.avenuecode.exceptions.NoSuchRouteException;
import com.avenuecode.rest.dtos.AvailableRoutesDto;
import com.avenuecode.rest.dtos.PathDistanceDto;
import com.avenuecode.rest.dtos.PathRouteDto;
import com.avenuecode.utils.StringUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j
public class RoutesService {

    @Autowired
    private PathService pathService;

    public List<AvailableRoutesDto> availableRoutes(Graph graph, String town1, String town2, int maxStops) {
        List<AvailableRoutesDto> availableRoutes = new ArrayList<>();
        List<PathRouteDto> routes = pathService.createWholeRoutesForPath(graph, town1, town2);

        routes.forEach(route -> {
            List<String> paths = route.getPath();
            AvailableRoutesDto availableRoute = AvailableRoutesDto.builder()
                    .route(StringUtils.replaceBracketsAndCommas(paths.toString()))
                    .stops(paths.size() - 1)
                    .build();
            availableRoutes.add(availableRoute);
        });
        return filterRoutesByMaxStops(maxStops, availableRoutes);
    }

    public PathDistanceDto sumDistanceForPath(Graph graph, List<String> towns) {
        return PathDistanceDto.builder()
                .distance(distanceForPath(graph.getRoutes(), towns))
                .build();
    }

    private Integer distanceForPath(List<Routes> routes, List<String> towns) {
        List<Pair<String, Pair<String, Integer>>> adjacencyPairs = buildAdjacencyPairs(routes);
        Integer distance = 0;

        for (int i = 0; i < towns.size() - 1; i += 1) {
            int index = i;
            distance += adjacencyPairs.stream()
                    .filter(pair -> pair.getFirst().contentEquals(towns.get(index)) && pair.getSecond().getFirst().contentEquals(towns.get(index + 1)))
                    .map(pair -> pair.getSecond().getSecond())
                    .findFirst()
                    .orElseThrow(() -> new NoSuchRouteException("NO SUCH ROUTE"));
        }
        return distance;
    }

    private List<Pair<String, Pair<String, Integer>>> buildAdjacencyPairs(List<Routes> routes) {
        List<Pair<String, Pair<String, Integer>>> pairs = new ArrayList<>();
        routes.forEach(route -> {
            pairs.add(Pair.of(route.getSource(), Pair.of(route.getTarget(), route.getDistance())));
        });
        return pairs;
    }

    private List<AvailableRoutesDto> filterRoutesByMaxStops(Integer maxStops, List<AvailableRoutesDto> availableRoutes) {
        return availableRoutes.stream()
                .filter(route -> route.getStops() <= maxStops)
                .peek(route -> log.info(String.format("Route '%s' was found!", route.getRoute())))
                .collect(Collectors.toList());
    }
}
