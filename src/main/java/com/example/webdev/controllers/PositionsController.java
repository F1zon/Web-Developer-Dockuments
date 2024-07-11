package com.example.webdev.controllers;

import com.example.webdev.model.Positions;
import com.example.webdev.service.PositionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PositionsController {
    private final PositionsServiceImpl positionsService;

    @Autowired
    public PositionsController(PositionsServiceImpl positionsService) {
        this.positionsService = positionsService;
    }


    @PostMapping(value = "/setPost")
    public ResponseEntity<?> setPost(@RequestBody Positions positions) {
        positionsService.create(positions);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<List<Positions>> getAll() {
        final List<Positions> positions = positionsService.readAll();
        return positions != null && !positions.isEmpty()
                ? new ResponseEntity<>(positions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
