package com.avenuecode.entities.mappers;

import com.avenuecode.entities.Graph;
import com.avenuecode.entities.mappers.commons.BeanMapper;
import com.avenuecode.rest.dtos.GraphDto;
import org.mapstruct.Mapper;

@Mapper(uses = RoutesBeanMapper.class)
public interface GraphBeanMapper extends BeanMapper<Graph, GraphDto> {

}
