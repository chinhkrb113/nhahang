package com.example.nhahang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhahang.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    
}
