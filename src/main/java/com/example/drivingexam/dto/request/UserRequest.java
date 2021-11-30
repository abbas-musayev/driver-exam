package com.example.drivingexam.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequest {
    Long id;
    String fullName;
    String username;
    String password;
    String email;
    String phone;
    Boolean approved;
    LocalDateTime createdDate;
}
