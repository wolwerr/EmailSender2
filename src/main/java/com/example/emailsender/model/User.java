package com.example.emailsender.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class User{

    private String name;
    private String email;
    private String phone;
    private String message;
}
