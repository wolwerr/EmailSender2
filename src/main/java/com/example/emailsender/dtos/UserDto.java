package com.example.emailsender.dtos;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String message;

}
