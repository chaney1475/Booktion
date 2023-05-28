package com.project.Booktion.repository;

import com.project.Booktion.model.TempOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TempOrderRepository extends JpaRepository<TempOrder,Long> {
    List<TempOrder> findByUserId(String userId);
}
