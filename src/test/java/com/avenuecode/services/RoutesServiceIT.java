package com.avenuecode.services;

import com.avenuecode.AvenueCodeAssessmentApplication;
import com.avenuecode.entities.Graph;
import com.avenuecode.entities.GraphFixture;
import com.avenuecode.exceptions.NoSuchRouteException;
import com.avenuecode.rest.dtos.AvailableRoutesDto;
import com.avenuecode.rest.dtos.AvailableRoutesDtoFixture;
import com.avenuecode.rest.dtos.PathDistanceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvenueCodeAssessmentApplication.class)
@ActiveProfiles("test")
public class RoutesServiceIT {

    @Autowired
    private RoutesService service;

    //~--   availableRoutes
    @Test
    public void availableRoutesTest() {
        int maxStops = 2;
        String town1 = "A", town2 = "C";
        Graph graph = GraphFixture.newGraphFixture();

        AvailableRoutesDto pathRouteAC1 = AvailableRoutesDtoFixture.availableRouteAC1();
        AvailableRoutesDto pathRouteAC2 = AvailableRoutesDtoFixture.availableRouteAC2();
        List<AvailableRoutesDto> availableRoutes = service.availableRoutes(graph, town1, town2, maxStops);

        assertThat(availableRoutes.size()).isEqualTo(2);
        assertThat(availableRoutes).contains(pathRouteAC1);
        assertThat(availableRoutes).contains(pathRouteAC2);
    }

    //~--   sumDistanceForPath
    @Test
    public void sumDistanceForPathTest() {
        Graph graph = GraphFixture.newGraphFixture();
        List<String> towns = Arrays.asList("A", "B", "C");

        PathDistanceDto pathSumDistance = service.sumDistanceForPath(graph, towns);
        assertThat(pathSumDistance).isNotNull();
        assertThat(pathSumDistance.getDistance()).isEqualTo(9);
    }

    @Test(expected = NoSuchRouteException.class)
    public void throwExceptionWhenThereIsNoRoute() {
        Graph graph = GraphFixture.newGraphFixture();
        List<String> towns = Arrays.asList("A", "E", "C");

        service.sumDistanceForPath(graph, towns);
    }
}