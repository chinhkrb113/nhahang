package com.example.nhahang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nhahang.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    
    @Query(value ="Select * from Orders where user_id = :id order by id desc",nativeQuery = true)
    List<Order> getOrderByUser(long id);
    Optional<Order> findByOrderCode(String code);   

    
    @Query(value ="SELECT o.*, u.* FROM orders as o JOIN order_detail as u ON o.id = u.order_id WHERE o.id = :id", nativeQuery = true)
    List<Order> getListOrder(long id);
}
