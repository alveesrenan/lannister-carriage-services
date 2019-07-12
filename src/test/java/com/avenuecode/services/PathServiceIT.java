package com.avenuecode.services;

import com.avenuecode.entities.Graph;
import com.avenuecode.entities.GraphFixture;
import com.avenuecode.entities.Routes;
import com.avenuecode.entities.RoutesFixture;
import com.avenuecode.rest.dtos.PathRouteDto;
import com.avenuecode.rest.dtos.PathRouteDtoFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PathServiceIT {

    @Spy
    private PathService service;

    //~--   shortestPath
    @Test
    public void shortestPathTest() {
        String town1 = "A", town2 = "C";
        Graph graph = GraphFixture.newGraphFixture();
        PathRouteDto shortestPath = PathRouteDtoFixture.routeAC1();

        PathRouteDto shortestPathGet = service.shortestPath(graph, town1, town2);

        assertThat(shortestPathGet.getDistance()).isEqualTo(shortestPath.getDistance());
        assertThat(shortestPathGet.getPath()).isEqualTo(shortestPath.getPath());
    }

    @Test
    public void returnEmptyTownsDistanceBySameTowns() {
        String town1 = "A", town2 = "A";
        Graph graph = GraphFixture.newGraphFixture();

        PathRouteDto shortestPath = service.shortestPath(graph, town1, town2);

        assertThat(shortestPath.getDistance()).isEqualTo(0);
        assertThat(shortestPath.getPath()).isNull();

        verify(service, times(0)).createWholeRoutesForPath(graph, town1, town2);
    }

    @Test
    public void returnNonExistentRouteBetweenTowns() {
        String town1 = "A", town2 = "C";
        Graph graph = GraphFixture.newGraphFixture();

        doReturn(Collections.emptyList()).when(service).createWholeRoutesForPath(graph, town1, town2);

        PathRouteDto shortestPath = service.shortestPath(graph, town1, town2);

        assertThat(shortestPath.getDistance()).isEqualTo(-1);
        assertThat(shortestPath.getPath()).isNull();
    }

    //~--   createWholeRoutesForPath
    @Test
    public void createWholeRoutesForPathTest() {
        String town1 = "A", town2 = "C";
        Graph graph = GraphFixture.newGraphFixture();
        List<PathRouteDto> pathRoutes = PathRouteDtoFixture.pathRoutes3Stops();

        List<PathRouteDto> pathRoutesGet = service.createWholeRoutesForPath(graph, town1, town2);

        assertThat(pathRoutesGet).isEqualTo(pathRoutes);
    }

    @Test
    public void returnSameDistanceBetweenTownsDueToItsAlreadyLinked() {
        String town1 = "A", town2 = "B";
        Graph graph = GraphFixture.newGraphFixture();
        Routes route = RoutesFixture.newRoutesFixtureAB();

        List<PathRouteDto> shortestPath = service.createWholeRoutesForPath(graph, town1, town2);

        PathRouteDto uniquePath = shortestPath.get(0);
        assertThat(uniquePath.getDistance()).isEqualTo(route.getDistance());
        assertThat(uniquePath.getPath().contains(route.getSource()));
        assertThat(uniquePath.getPath().contains(route.getTarget()));
    }
}