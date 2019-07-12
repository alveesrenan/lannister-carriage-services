package com.avenuecode.rest.controllers;

import com.avenuecode.dtos.PathsDto;
import com.avenuecode.dtos.PathsDtoFixture;
import com.avenuecode.dtos.RoutesDataDto;
import com.avenuecode.dtos.RoutesDataDtoFixture;
import com.avenuecode.rest.controllers.commons.RestControllerIT;
import com.avenuecode.rest.dtos.GraphDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GraphControllerIT extends RestControllerIT {

    private Gson gson = new Gson();

    //~--   createGraph
    @Test
    public void httpStatusCreatedWhenCreateGraph() throws Exception {
        RoutesDataDto data = RoutesDataDtoFixture.newRoutes();
        
        mockMvc.perform(post("/graph")
                .contentType(contentType)
                .content(gson.toJson(data)))
                .andExpect(status().isCreated());
    }

    //~--   getGraph
    @Test
    public void httpStatusOkWhenGettingExistentGraph() throws Exception {
        RoutesDataDto data = RoutesDataDtoFixture.newRoutes();

        // first creating graph
        MvcResult result = mockMvc.perform(post("/graph")
                .contentType(contentType)
                .content(gson.toJson(data)))
                .andExpect(status().isCreated())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        Long graphId = gson.fromJson(response, GraphDto.class).getId();

        mockMvc.perform(get("/graph/" + graphId)
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void httpStatusNotFoundWhenThereIsNoGraph() throws Exception {
        mockMvc.perform(get("/graph/" + 1000L)
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    //~--   findAvailableRoutesBetweenTowns
    @Test
    public void httpStatusOkWhenThereAreAvailableRoutes() throws Exception {
        Integer maxStops = 2;
        String town1 = "A", town2 = "C";
        RoutesDataDto data = RoutesDataDtoFixture.newRoutes();

        // first creating graph
        MvcResult result = mockMvc.perform(post("/graph")
                .contentType(contentType)
                .content(gson.toJson(data)))
                .andExpect(status().isCreated())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        Long graphId = gson.fromJson(response, GraphDto.class).getId();

        mockMvc.perform(post("/routes/" + graphId + "/from/" + town1 + "/to/" + town2)
                .param("maxStops", maxStops.toString())
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    //~--   findDistanceForPath
    @Test
    public void httpStatusOkWhenThereIsDistanceForPath() throws Exception {
        RoutesDataDto data = RoutesDataDtoFixture.newRoutes();

        // first creating graph
        MvcResult result = mockMvc.perform(post("/graph")
                .contentType(contentType)
                .content(gson.toJson(data)))
                .andExpect(status().isCreated())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        Long graphId = gson.fromJson(response, GraphDto.class).getId();

        PathsDto path = PathsDtoFixture.newPath();
        mockMvc.perform(post("/distance/" + graphId)
                .content(gson.toJson(path))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    //~--   findShortestPathBetweenTowns
    @Test
    public void httpStatusOkWhenThereIsShortestPathBetweenTowns() throws Exception {
        RoutesDataDto data = RoutesDataDtoFixture.newRoutes();

        // first creating graph
        MvcResult result = mockMvc.perform(post("/graph")
                .contentType(contentType)
                .content(gson.toJson(data)))
                .andExpect(status().isCreated())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        Long graphId = gson.fromJson(response, GraphDto.class).getId();

        String town1 = "A", town2 = "C";
        mockMvc.perform(post("/distance/" + graphId + "/from/" + town1 + "/to/" + town2)
                .contentType(contentType))
                .andExpect(status().isOk());
    }
}