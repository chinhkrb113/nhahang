package com.example.nhahang.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nhahang.entity.VoucherUser;
import com.example.nhahang.model.request.CreateVoucherUserRequest;
import com.example.nhahang.repository.UserRepository;
import com.example.nhahang.repository.VoucherRepository;
import com.example.nhahang.repository.VoucherUserRepository;
import com.example.nhahang.service.VoucherUserService;

@Service
public class VoucherUserServiceImpl implements VoucherUserService {

	@Autowired
    private VoucherUserRepository voucherUserRepository;
	@Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private UserRepository userRepository;
	@Override
    public VoucherUser createVoucherUser(CreateVoucherUserRequest request) {
        VoucherUser voucherUser = new VoucherUser();
        // Set Voucher
        voucherRepository.findById(request.getVoucherId()).ifPresent(voucherUser::setVoucher);
        // Set User
        userRepository.findById(request.getUserId()).ifPresent(voucherUser::setUser);
        // Set CreateAt
        LocalDateTime createdAt = request.getCreateAt();
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        voucherUser.setCreateAt(createdAt);
        // Save and return
        return voucherUserRepository.save(voucherUser);
    }
}
