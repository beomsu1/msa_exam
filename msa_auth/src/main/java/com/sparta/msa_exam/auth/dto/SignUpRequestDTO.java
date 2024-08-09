package com.sparta.msa_exam.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequestDTO {

    private String id;
    private String password;
    private String username;
    private String role;

}
