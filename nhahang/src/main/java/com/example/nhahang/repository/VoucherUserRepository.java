package com.example.nhahang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhahang.entity.VoucherUser;

@Repository
public interface VoucherUserRepository  extends JpaRepository<VoucherUser,Long> {

}
