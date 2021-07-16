package com.zongs365.service.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class TestController {

    @GetMapping(value = "/hello")
    @ApiOperation(value = "hello")
    public String hello() {
        return "hello";
    }
}
