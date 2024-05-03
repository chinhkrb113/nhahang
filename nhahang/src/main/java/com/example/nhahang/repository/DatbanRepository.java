package com.example.nhahang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhahang.entity.Datban;
@Repository
public interface DatbanRepository extends JpaRepository<Datban,Long> {

}
