package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.Serializable;

@RestController
@RequestMapping("/record")
@Controller
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;

    @PostMapping("/createRecord")
    public ResponseEntity<Record> createRecord(@RequestBody ExampleRequest[] requests) {

        String adminID = null;
        String userID = null;
        String recordTime = null;
        for (ExampleRequest request : requests) {
            adminID = request.getString1();
            userID = request.getString2();
            recordTime = request.getString3();
        }


        RestTemplate restTemplate = new RestTemplate();
        String adminResourceUrl
                = "http://localhost:8087/admin/getAdmins/" + adminID;
        Admin admin = restTemplate
                .getForObject(adminResourceUrl , Admin.class);
        String userResourceUrl
                = "http://localhost:8085/demo/getUsers/" + userID;
        User user = restTemplate
                .getForObject(userResourceUrl , User.class);

        Record record = new Record(admin, user, recordTime);
        Record createRecord  = recordRepository.save(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(createRecord);
    }
}

class ExampleRequest implements Serializable {
    private String string1;
    private String string2;
    private String string3;

    // Constructors
    public ExampleRequest() {
    }

    public ExampleRequest(String string1, String string2, String string3) {
        this.string1 = string1;
        this.string2 = string2;
        this.string3 = string3;
    }

    // Getters and setters
    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }
}
