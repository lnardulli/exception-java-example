package com.exception.demo.controller;

import com.exception.demo.component.YoungService;
import com.exception.demo.model.Young;
import com.exception.demo.model.YoungResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/young")
public class YoungMachine {

    private YoungService youngService;

    @Autowired
    public YoungMachine(YoungService youngService) {
        this.youngService = youngService;
    }

    @GetMapping(path = "/find/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<YoungResponse> findPersonByName(@PathVariable String name) throws NotFoundException {
        return new ResponseEntity<>(this.youngService.findByName(name), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<YoungResponse> findPersonByName(@RequestBody Young young) {
        this.youngService.createPersona(young);
        return new ResponseEntity<>(new YoungResponse(), HttpStatus.OK);
    }


}
