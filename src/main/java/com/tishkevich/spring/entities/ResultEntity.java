package com.tishkevich.spring.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Table(name = "result")
@Entity
public class ResultEntity{

    @Id
    @Column(name = "username", nullable = false, length = 36)
    private String username;

    @Column(name = "result_value", nullable = false)
    private Integer resultValue;

    @Column(name = "date", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate date;
}
