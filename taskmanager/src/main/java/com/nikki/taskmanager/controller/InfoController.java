package com.nikki.taskmanager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/info")
public class InfoController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAppInfo() {
        Map<String, Object> response = new HashMap<>();

        response.put("appName", appName);
        response.put("appVersion", appVersion);

        return ResponseEntity.ok(response);
    }
}
