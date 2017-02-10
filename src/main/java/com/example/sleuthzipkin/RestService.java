package com.example.sleuthzipkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestService {

    public static final int PORT = 8080;
    RestTemplate restTemplate;

    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(path = "/superEcho")
    public String doEcho(@RequestBody String buddy)
    {
        return "super " + buddy;
    }

    @PostMapping(path = "/hello")
    public String doHello(@RequestBody String buddy)
    {
        String superBuddy = restTemplate.postForObject("http://localhost:"+ PORT + "/superEcho", buddy, String.class);
        return "Hello "  + superBuddy + "!";
    }
}
