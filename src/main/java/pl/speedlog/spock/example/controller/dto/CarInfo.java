package pl.speedlog.spock.example.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarInfo {

    @NotNull(message = "VIN must be provided.")
    @Size(min = 17, max = 17, message = "VIN must be 17 characters long.")
    private String vin;
    @NotNull(message = "Brand must be provided.")
    @Size(min = 1, max = 50, message = "Brand must be at least 1 character and at most 50 characters long.")
    private String brand;
    @NotNull(message = "Model must be provided.")
    @Size(min = 1, max = 50, message = "Model must be at least 1 character and at most 50 characters long.")
    private String model;

}
