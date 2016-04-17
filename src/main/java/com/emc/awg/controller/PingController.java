package com.emc.awg.controller;

import com.shedhack.exception.core.BusinessException;
import com.shedhack.thread.context.annotation.ThreadContext;
import com.shedhack.trace.request.api.constant.HttpHeaderKeysEnum;
import com.shedhack.trace.request.api.threadlocal.RequestThreadLocalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;

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

    @Autowired
    RestTemplate template;

    @RequestMapping("/ping")
    public ResponseEntity<String> ping() throws MalformedURLException {



/*
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaderKeysEnum.CALLER_ID.key(), RequestThreadLocalHelper.get().getRequestId());
        headers.add(HttpHeaderKeysEnum.GROUP_ID.key(), RequestThreadLocalHelper.get().getGroupId());

        HttpEntity<String> request = new HttpEntity<>("pong", headers);
        template.postForObject(new URL("http://localhost:8080/pong").toString(), request, String.class);
*/

        return new ResponseEntity<>(appName + ", started " + timestamp, HttpStatus.OK);
    }

    @RequestMapping("/pong")
    @ThreadContext
    public ResponseEntity<String> pong(String message) {

        throw BusinessException.builder("Something horrible happened").generateId().build();

        //return new ResponseEntity<>(appName + ", started " + timestamp, HttpStatus.OK);
    }

}
