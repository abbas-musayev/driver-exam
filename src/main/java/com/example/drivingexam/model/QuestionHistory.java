package com.example.drivingexam.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "answer_history")
public class QuestionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Boolean isTrue;
    String answer;

    @ManyToOne(cascade = CascadeType.ALL)
    Question question;

    @ManyToOne(cascade = CascadeType.ALL)
    Exam exam;

}
