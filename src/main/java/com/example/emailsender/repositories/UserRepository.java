package com.example.emailsender.repositories;

import com.example.emailsender.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
