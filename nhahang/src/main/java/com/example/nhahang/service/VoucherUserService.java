package com.example.nhahang.service;

import com.example.nhahang.entity.VoucherUser;
import com.example.nhahang.model.request.CreateVoucherUserRequest;

public interface VoucherUserService {
	VoucherUser createVoucherUser(CreateVoucherUserRequest request);
}
