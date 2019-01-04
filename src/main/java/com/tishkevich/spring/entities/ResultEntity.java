package com.tishkevich.spring.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "result")
public class ResultEntity{

    @Column(name = "username", nullable = false, length = 36)
    private String username;

    @Column(name = "result_value", nullable = false)
    private Integer resultValue;

    @Column(name = "date", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateType")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
}
