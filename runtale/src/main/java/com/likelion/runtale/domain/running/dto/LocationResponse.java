package com.likelion.runtale.domain.running.dto;

import com.likelion.runtale.domain.running.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationResponse {
    private Double latitude;
    private Double longitude;

    public LocationResponse(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
