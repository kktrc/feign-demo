package com.pzzls.feigndemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/tree")
public class TreeController {


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<String> list(@RequestParam("treeName") String treeName) {


        List<String> treeList = new ArrayList<>();
        treeList.add(treeName);
        return treeList;
    }
}
