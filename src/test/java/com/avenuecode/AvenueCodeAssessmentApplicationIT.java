package com.avenuecode;

import com.avenuecode.rest.controllers.commons.RestControllerIT;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AvenueCodeAssessmentApplicationIT extends RestControllerIT {

    @Test
    public void httpStatusOkToHealhCheck() throws Exception {
        mockMvc.perform(get("/health")
                .contentType(contentType))
                .andExpect(status().isOk());
    }
}