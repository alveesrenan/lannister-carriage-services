package com.avenuecode.mediators;

import com.avenuecode.AvenueCodeAssessmentApplication;
import com.avenuecode.dtos.RoutesDto;
import com.avenuecode.dtos.RoutesDtoFixture;
import com.avenuecode.entities.Graph;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvenueCodeAssessmentApplication.class)
@ActiveProfiles("test")
public class GraphMediatorIT {

    @Autowired
    private GraphMediator mediator;

    //~--   createGraph
    @Test
    public void createTest() {
        List<RoutesDto> routesDtos = RoutesDtoFixture.newRoutesDtoFixtureList();
        assertThat(mediator.createGraph(routesDtos)).isNotNull();
    }

    //~--   getGraph
    @Test
    public void getGraphTest() {
        List<RoutesDto> routesDtos = RoutesDtoFixture.newRoutesDtoFixtureList();

        Long id = mediator.createGraph(routesDtos).getId();

        assertThat(mediator.getGraph(id)).isNotNull();
    }
}