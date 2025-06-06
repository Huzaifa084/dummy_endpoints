package com.dummy.dummy_endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class DummyController {

    @GetMapping("/hello")
    public ResponseEntity<String> hi(){
       return ResponseEntity.ok("Hello World");
    }
}
