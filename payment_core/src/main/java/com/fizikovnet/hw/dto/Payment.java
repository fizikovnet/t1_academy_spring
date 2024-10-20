package com.fizikovnet.hw.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class Payment {

    @NotNull(message = "Field productId must be specified")
    private final Integer productId;

    @NotNull(message = "Field writeOff must be true or false")
    private final Boolean writeOff;

    @NotNull
    @DecimalMin(value = "0.01", message = "Field summa must be greater than 0.01!")
    private final Double summa;

    public Payment(Integer id, Boolean writeOff, Double summa) {
        this.productId = id;
        this.writeOff = writeOff;
        this.summa = summa;
    }

    public Integer getProductId() {
        return productId;
    }

    public Boolean getWriteOff() {
        return writeOff;
    }

    public Double getSumma() {
        return summa;
    }
}
