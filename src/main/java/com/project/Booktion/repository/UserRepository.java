package com.project.Booktion.repository;

import com.project.Booktion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByUserIdAndPassword(String userId, String password);

    //@Query(value = "SELECT u FROM User u WHERE u.userId = :userId AND u.password = :password", nativeQuery = true)
    //User findByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);

}
