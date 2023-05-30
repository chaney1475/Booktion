package com.project.Booktion.repository;

import com.project.Booktion.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findById(String userId);
}
