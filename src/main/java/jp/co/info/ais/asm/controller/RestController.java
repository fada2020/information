package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.SampleUser;


@Controller
@ResponseBody
public class RestController {

	private static final Logger logger = LogManager.getLogger(RestController.class);

    @RequestMapping("/getuserlist")
    public List<SampleUser> getuserlist() {
        List<SampleUser> list = new ArrayList<SampleUser>();
        for (int i=0; i<100; i++) {
            SampleUser user= new SampleUser(
                    String.valueOf(i+1),
                    String.format("u%04d@sample.com",i+1));
            list.add(user);
            logger.debug(user.toString());
        }
        return list;
    }
}