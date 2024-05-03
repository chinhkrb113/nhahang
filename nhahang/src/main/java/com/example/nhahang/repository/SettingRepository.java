package com.example.nhahang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhahang.entity.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting,Long> {
    
}
