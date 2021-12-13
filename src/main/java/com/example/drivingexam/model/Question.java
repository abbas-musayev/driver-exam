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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String answer;

    String name;

    @OneToOne(cascade = CascadeType.ALL)
    File file;

    @ManyToOne(cascade = CascadeType.ALL)
    Section section;

}
