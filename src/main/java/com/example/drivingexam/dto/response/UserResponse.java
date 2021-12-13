package com.example.drivingexam.dto.response;

import com.example.drivingexam.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    Long id;
    String fullName;
    String username;
    String password;
    String email;
    String phone;

    List<ExamResponse> exams;
}
