package com.avenuecode.entities.mappers;

import com.avenuecode.entities.Graph;
import com.avenuecode.entities.GraphFixture;
import com.avenuecode.rest.dtos.GraphDto;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphBeanMapperTest {

    private GraphBeanMapper mapper;

    @Before
    public void before() {
        this.mapper = Mappers.getMapper(GraphBeanMapper.class);
    }

    @Test
    public void adaptAllFields() {
        Graph graph = GraphFixture.newGraphFixture();
        GraphDto graphDto = mapper.toTarget(graph);

        assertThat(graph.getId()).isEqualTo(graphDto.getId());
        assertThat(graphDto.getRoutes()).isNotNull();
        assertThat(graphDto.getRoutes()).isNotEmpty();
    }
}