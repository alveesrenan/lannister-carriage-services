package com.avenuecode.entities.mappers;

import com.avenuecode.dtos.RoutesDto;
import com.avenuecode.entities.Routes;
import com.avenuecode.entities.RoutesFixture;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RoutesBeanMapperTest {

    private RoutesBeanMapper mapper;

    @Before
    public void before() {
        this.mapper = Mappers.getMapper(RoutesBeanMapper.class);
    }

    @Test
    public void adaptAllFields() {
        Routes routes = RoutesFixture.newRoutesFixtureAB();
        RoutesDto routesDto = mapper.toTarget(routes);

        assertThat(routesDto.getSource()).isEqualTo(routes.getSource());
        assertThat(routesDto.getTarget()).isEqualTo(routes.getTarget());
        assertThat(routesDto.getDistance()).isEqualTo(routes.getDistance());
    }
}