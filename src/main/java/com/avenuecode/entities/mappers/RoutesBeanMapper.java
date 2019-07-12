package com.avenuecode.entities.mappers;

import com.avenuecode.dtos.RoutesDto;
import com.avenuecode.entities.Routes;
import com.avenuecode.entities.mappers.commons.BeanMapper;
import org.mapstruct.Mapper;

@Mapper
public interface RoutesBeanMapper extends BeanMapper<Routes, RoutesDto> {

}
