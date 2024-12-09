package com.example;

import com.example.controller.AddController;
import com.example.io.Request;
import com.example.io.Response;
import com.example.service.AddService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@WebMvcTest(controllers = AddController.class)
@Import(AddService.class)
public class AddControllerTest {

    @Autowired
    AddService fooService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Request body has values 2 and 3, expectation is that response is 2 + 3 = 5
     * Single request works correctly
     */
    @Test
    public void testController_singleRequest() throws Exception {
        Request request = new Request();
        request.setValues(List.of(2, 3));
        Response response = new Response(5);
        mockMvc.perform(post("/v1/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    /**
     * We send 2 requests in this test
     * Request1 body has values 2 and 3, expectation is that response is 2 + 3 = 5
     * Request2 body has values 1 and 8, expectation is that response is 1 + 8 = 9
     * This test fails (expected and actual don't match)
     * java.lang.AssertionError: message
     */
    @Test
    @SuppressWarnings("all")
    public void testController_multipleRequests() throws Exception {
        Request request1 = new Request();
        request1.setValues(List.of(2, 3));
        Response response1 = new Response(5);

        Request request2 = new Request();
        request2.setValues(List.of(1, 8));
        Response response2 = new Response(9);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<?> future1 = executorService.submit(() -> {
            try {
                mockMvc.perform(post("/v1/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request1)))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(response1)));
            } catch (Exception e) {
                // swallow
            }
        });

        Future<?> future2 = executorService.submit(() -> {
            try {
                mockMvc.perform(post("/v1/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request2)))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(response2)));
            } catch (Exception e) {
               // swallow
            }
        });

        future1.get();
        future2.get();

        executorService.shutdown();
    }
}
