package com.pzzls.feigndemo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @RequestMapping(method = RequestMethod.POST)
    public User users(@RequestBody User user) {

        return user;
    }
}
