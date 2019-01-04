package com.tishkevich.spring.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "result")
public class ResultEntity{

    @Column(name = "username")
    private String username;

    @Column(name = "result_value")
    private Integer resultValue;

    @Column(name = "date")
    private LocalDate date;
}
