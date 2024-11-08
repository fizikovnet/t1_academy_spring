package com.fizikovnet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@Entity
@Table(name = "limits")
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer clientId;

    @Column(nullable = false)
    private BigDecimal dailyLimit = BigDecimal.valueOf(10000);

    @Column(nullable = false)
    private BigDecimal remainingLimit = BigDecimal.valueOf(10000);

    @Column(nullable = false)
    private LocalDate lastReset = LocalDate.now();

}
