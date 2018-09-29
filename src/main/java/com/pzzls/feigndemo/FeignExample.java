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


    interface GitHub {


        class Repository {
            String name;
        }


        class Contributor {
            String login;
        }


        @RequestLine("GET /users/{username}/repos?sort=full_name")
        List<Repository> repos(@Param("username") String owner);


        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);


        @RequestLine("GET /tree/list?treeName={treeName}")
        List<String> treeList(@Param("treeName") String treeName);


        @Headers({"Content-Type: application/json","Accept: application/json"})
        @RequestLine("POST /users")
        User user(User user);


        static GitHub connect() {
            Decoder decoder = new GsonDecoder();
            return Feign.builder()
                    .decoder(decoder)
                    .encoder(new GsonEncoder())
                    .options(new Request.Options(10 * 1000, 35 * 1000))
                    .target(GitHub.class, "http://localhost:8080");
        }


        default List<String> contributors(String owner) {
            return repos(owner).stream()
                    .flatMap(repo -> contributors(owner, repo.name).stream())
                    .map(c -> c.login)
                    .distinct()
                    .collect(Collectors.toList());
        }

    }


    public static void main(String args[]) {

        GitHub github = GitHub.connect();


        System.out.println(github.treeList("Hello World!"));



        User user = new User();
        user.setName("Boy");

        github.user(user);


//
//        System.out.println("Let's fetch and print a list of the contributors to this org.");
//        List<String> contributors = github.contributors("netflix");
//        for (String contributor : contributors) {
//            System.out.println(contributor);
//        }
//
//        System.out.println("Now, let's cause an error.");
//        github.contributors("netflix", "some-unknown-project");


    }
}
