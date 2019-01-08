package com.tishkevich.spring.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Table(name = "result")
@Entity
public class ResultEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 36)
    private String username;

    @Column(name = "result_value", nullable = false)
    private Integer resultValue;

    @Column(name = "date", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate date;
}
