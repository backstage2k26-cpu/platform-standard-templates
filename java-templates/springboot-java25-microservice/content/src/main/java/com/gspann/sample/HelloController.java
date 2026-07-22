package com.gspann.sample;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final String environmentName;

    public HelloController(Environment environment) {
        String[] activeProfiles = environment.getActiveProfiles();
        this.environmentName = activeProfiles.length == 0 ? "local" : String.join(",", activeProfiles);
    }

    @GetMapping("/")
    public String home() {
        return hello();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hellow GSPANN , Env: " + environmentName;
    }
}
