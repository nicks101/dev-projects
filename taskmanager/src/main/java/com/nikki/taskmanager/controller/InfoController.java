package com.nikki.taskmanager.controller;

import com.nikki.taskmanager.config.AppProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/info")
public class InfoController {

    private final AppProperties appProperties;

    public InfoController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAppInfo() {
        Map<String, Object> response = new HashMap<>();

        response.put("appName", appProperties.getName());
        response.put("appVersion", appProperties.getVersion());

        return ResponseEntity.ok(response);
    }
}
