package com.tishkevich.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "question")
public class QuestionDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String questionText;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;


    @NotNull
    private String firstAnswer;

    @NotNull
    private String secondAnswer;

    @NotNull
    private String thirdAnswer;

    @NotNull
    private String forthAnswer;

    @NotNull
    private int correctAnswerNumber;
}
