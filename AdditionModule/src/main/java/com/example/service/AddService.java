package com.example.service;

import com.example.io.Request;
import com.example.io.Response;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class AddService {

    private Request request;

    public Response execute(Request request) throws Exception {
        this.request = request;
        return addNumbers();
    }

    private Response addNumbers() throws Exception {
        // Introduce a delay of 5 seconds to mimic real time heavy processing.
        Thread.sleep(5000);
        Integer sum = this.request.getValues().stream().reduce(Integer::sum).get();
        return new Response(sum);
    }
}
