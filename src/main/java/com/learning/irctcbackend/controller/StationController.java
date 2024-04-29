package com.learning.irctcbackend.controller;

import com.learning.irctcbackend.model.Station;
import com.learning.irctcbackend.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StationController {

    private final StationService stationService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Station> add(@RequestBody Station station) {
        return new ResponseEntity<>(stationService.add(station), HttpStatus.CREATED);
    }
}
