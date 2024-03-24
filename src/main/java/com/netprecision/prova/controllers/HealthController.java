package com.netprecision.prova.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "https://prova-netprecision-web.vercel.app/")
public class HealthController
{
    @GetMapping
    public String get() {
        return "API is running...";
    }
}
