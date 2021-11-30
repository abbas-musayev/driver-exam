package com.example.drivingexam.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Exam {
    @Id
    Long id;

    @CreationTimestamp
    LocalDateTime createdDate;

    @ManyToOne
    User user;

    @OneToMany
    List<QuestionHistory> questionHistories;



}
