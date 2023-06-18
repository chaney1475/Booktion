package com.project.Booktion.repository;

import com.project.Booktion.model.Address;
import com.project.Booktion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
    User deleteByUserId(String userId);
    User findByUserIdAndPassword(String userId, String password);

}
