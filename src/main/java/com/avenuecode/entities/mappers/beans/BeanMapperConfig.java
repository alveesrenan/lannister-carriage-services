package com.avenuecode.entities.mappers.beans;

import com.avenuecode.entities.mappers.GraphBeanMapper;
import com.avenuecode.entities.mappers.RoutesBeanMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanMapperConfig {

    @Bean
    public GraphBeanMapper graphBeanMapper() {
        return Mappers.getMapper(GraphBeanMapper.class);
    }

    @Bean
    public RoutesBeanMapper routesBeanMapper() {
        return Mappers.getMapper(RoutesBeanMapper.class);
    }
}
