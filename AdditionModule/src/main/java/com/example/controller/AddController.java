package com.example.controller;

import com.example.io.Request;
import com.example.io.Response;
import com.example.service.AddService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {

    private final AddService fooService;

    public AddController(AddService fooService) {
        this.fooService = fooService;
    }

    @PostMapping("/v1/add")
    public Response getFoo(@RequestBody Request request) throws Exception {
        return fooService.execute(request);
    }
}
