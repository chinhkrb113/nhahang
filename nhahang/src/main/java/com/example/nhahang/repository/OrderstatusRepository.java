package com.example.nhahang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhahang.entity.Orderstatus;

@Repository
public interface OrderstatusRepository extends JpaRepository<Orderstatus,Long> {

}
