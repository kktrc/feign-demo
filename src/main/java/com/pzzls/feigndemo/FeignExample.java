package com.pzzls.feigndemo;

import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.Request;
import feign.RequestLine;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

public class FeignExample {


    public static void main(String args[]) {

        Github github = Github.connect();

        System.out.println(github.treeList("Hello World!"));


        User user = new User();
        user.setName("Boy");

        github.user(user);

    }
}
