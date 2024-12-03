package org.example.parkingsystemv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CoordinateDTO {
    private double latitude;
    private double longitude;
}
