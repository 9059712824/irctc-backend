package com.learning.irctcbackend.dao;

import com.learning.irctcbackend.model.Station;
import org.apache.ibatis.annotations.Param;

import java.util.UUID;

public interface StationDao {
    Station getByName(@Param("name") String name);

    Station getByCode(@Param("code") String code);

    Station getByUuid(@Param("uuid") UUID uuid);

    int insert(@Param("station") Station station);
}
