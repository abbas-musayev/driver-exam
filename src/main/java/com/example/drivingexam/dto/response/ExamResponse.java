package com.example.drivingexam.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ExamResponse {

    Long id;
    UserResponse user;
    List<QuestionResponse> questions;
}
