package com.avenuecode.mediators;

import com.avenuecode.entities.Graph;
import com.avenuecode.entities.GraphFixture;
import com.avenuecode.rest.dtos.AvailableRoutesDto;
import com.avenuecode.rest.dtos.AvailableRoutesDtoFixture;
import com.avenuecode.rest.dtos.PathDistanceDto;
import com.avenuecode.rest.dtos.PathDistanceDtoFixture;
import com.avenuecode.rest.dtos.PathRouteDto;
import com.avenuecode.rest.dtos.PathRouteDtoFixture;
import com.avenuecode.services.GraphService;
import com.avenuecode.services.PathService;
import com.avenuecode.services.RoutesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GraphMediatorTest {

    @Spy
    @InjectMocks
    private GraphMediator mediator;

    @Mock
    private GraphService graphService;

    @Mock
    private RoutesService routesService;

    @Mock
    private PathService pathService;

    //~--   availableRoutesBetweenTowns
    @Test
    public void availableRoutesBetweenTownsTraceTest() {
        Long graphId = 1L;
        String town1 = "A", town2 = "C";
        Integer maxStops = 2;

        Graph graph = GraphFixture.newGraphFixture();
        doReturn(graph).when(graphService).get(graphId);

        List<AvailableRoutesDto> availableRoutes = AvailableRoutesDtoFixture.availableRoutes();
        doReturn(availableRoutes).when(routesService).availableRoutes(graph, town1, town2, maxStops);

        mediator.availableRoutesBetweenTowns(graphId, town1, town2, maxStops);

        verify(graphService).get(graphId);
        verify(routesService).availableRoutes(graph, town1, town2, maxStops);
    }

    //~--   distanceForPath
    @Test
    public void distanceForPathEmptyTownsList() {
        Long graphId = 1L;

        PathDistanceDto pathDistanceDto = mediator.distanceForPath(graphId, Collections.emptyList());

        assertThat(pathDistanceDto.getDistance()).isEqualTo(0);

        verify(graphService, times(0)).get(graphId);
        verify(routesService, times(0)).sumDistanceForPath(any(Graph.class), anyListOf(String.class));
    }

    @Test
    public void distanceForPathSingleList() {
        Long graphId = 1L;
        List<String> towns = Collections.singletonList("A");

        PathDistanceDto pathDistanceDto = mediator.distanceForPath(graphId, towns);

        assertThat(pathDistanceDto.getDistance()).isEqualTo(0);

        verify(graphService, times(0)).get(graphId);
        verify(routesService, times(0)).sumDistanceForPath(any(Graph.class), anyListOf(String.class));
    }

    @Test
    public void distanceForPathTraceTest() {
        Long graphId = 1L;
        List<String> towns = Arrays.asList("A", "B", "C");

        Graph graph = GraphFixture.newGraphFixture();
        doReturn(graph).when(graphService).get(graphId);

        PathDistanceDto pathDistanceDto = PathDistanceDtoFixture.pathDistance();
        doReturn(pathDistanceDto).when(routesService).sumDistanceForPath(graph, towns);

        mediator.distanceForPath(graphId, towns);

        verify(graphService).get(graphId);
        verify(routesService).sumDistanceForPath(graph, towns);
    }

    //~--   shortestPathBetweenTwoTowns
    @Test
    public void distanceBetweenSameTowns() {
        Long graphId = 1L;
        String town1 = "A", town2 = "A";

        PathRouteDto pathRouteDto = mediator.shortestPathBetweenTwoTowns(graphId, town1, town2);

        assertThat(pathRouteDto.getDistance()).isEqualTo(0);
        assertThat(pathRouteDto.getPath()).isNull();

        verify(graphService, times(0)).get(graphId);
        verify(pathService, times(0)).shortestPath(any(Graph.class), anyString(), anyString());
    }

    @Test
    public void distanceBetweenTwoTownsTraceTest() {
        Long graphId = 1L;
        String town1 = "A", town2 = "C";

        Graph graph = GraphFixture.newGraphFixture();
        doReturn(graph).when(graphService).get(graphId);

        PathRouteDto pathRouteDto = PathRouteDtoFixture.routeAC1();
        doReturn(pathRouteDto).when(pathService).shortestPath(graph, town1, town2);

        mediator.shortestPathBetweenTwoTowns(graphId, town1, town2);

        verify(graphService).get(graphId);
        verify(pathService).shortestPath(graph, town1, town2);
    }
}