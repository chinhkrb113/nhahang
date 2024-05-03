package com.example.nhahang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhahang.entity.About;
@Repository
public interface AboutRepository extends JpaRepository<About,Long> {
    
}
