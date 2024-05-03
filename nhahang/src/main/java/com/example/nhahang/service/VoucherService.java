package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.Voucher;
import com.example.nhahang.model.request.CreateVoucherRequest;

public interface VoucherService {
	
	List<Voucher> getListVoucher(long id);
	Voucher createVoucher(CreateVoucherRequest request);
	Voucher updateVoucher(long id, CreateVoucherRequest request);
	void deleteVoucher(long id);
	List<Voucher> getList();
	void enableVoucher(long id);
	Voucher updateVouchercount(long id,CreateVoucherRequest request);
}
