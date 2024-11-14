package com.example.tpjawrs.config;

import com.example.tpjawrs.controller.CompteRestController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class MyConfig extends ResourceConfig {
    public MyConfig() {
        register(CompteRestController.class);
    }
}
