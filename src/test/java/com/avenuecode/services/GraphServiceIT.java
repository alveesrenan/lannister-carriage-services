package com.avenuecode.services;

import com.avenuecode.AvenueCodeAssessmentApplication;
import com.avenuecode.dtos.RoutesDto;
import com.avenuecode.dtos.RoutesDtoFixture;
import com.avenuecode.exceptions.GraphNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvenueCodeAssessmentApplication.class)
@ActiveProfiles("test")
public class GraphServiceIT {

    @Autowired
    private GraphService service;

    //~--   create
    @Test
    public void createTest() {
        List<RoutesDto> routesDtos = RoutesDtoFixture.newRoutesDtoFixtureList();
        assertThat(service.create(routesDtos)).isNotNull();
    }

    @Test(expected = TransactionSystemException.class)
    public void throwExceptionWhenRoutesIsNull() {
        service.create(null);
    }

    //~--   get
    @Test
    public void getGraphTest() {
        List<RoutesDto> routesDtos = RoutesDtoFixture.newRoutesDtoFixtureList();
        Long id = service.create(routesDtos).getId();
        assertThat(service.get(id)).isNotNull();
    }

    @Test(expected = GraphNotFoundException.class)
    public void throwExceptionWhenThereIsNoGraph() {
        service.get(9999L);
    }
}