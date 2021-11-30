package com.example.drivingexam.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ExamRequest {
    Long id;
    LocalDateTime createdDate;

    UserRequest userDto;

    List<QuestionHistoryRequest> questionHistoriesDto;
}
