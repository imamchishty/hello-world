package com.emc.awg.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Returns HTTP 200 (OK)
 *
 * @author imamchishty
 */
@RestController
public class PingController {

    @Value("${spring.application.name}")
    private String appName;

    private Date timestamp = new Date();

    @RequestMapping("/ping")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<>(appName + ", started " + timestamp, HttpStatus.OK);
    }

}
