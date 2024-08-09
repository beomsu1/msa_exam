package com.sparta.msa_exam.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "uesrs")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    private String id;
    private String password;
    private String username;
    private String role;

    @CreatedDate
    private LocalDateTime createAt;


}
