package com.learning.irctcbackend.service.impl;

import com.learning.irctcbackend.dao.StationDao;
import com.learning.irctcbackend.exception.AlreadyFoundException;
import com.learning.irctcbackend.model.Station;
import com.learning.irctcbackend.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationDao stationDao;

    @Override
    public Station add(Station station) {
        if (stationDao.getByName(station.getName()) != null) {
            throw new AlreadyFoundException("Station already exists with name " + station.getName());
        } else if (stationDao.getByCode(station.getCode()) != null) {
            throw new AlreadyFoundException("Station already exists with code " + station.getCode());
        }
        station.setUuid(UUID.randomUUID());
        stationDao.insert(station);
        return station;
    }
}
