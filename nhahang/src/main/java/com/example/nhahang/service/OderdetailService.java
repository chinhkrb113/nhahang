package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.OrderDetail;

public interface OderdetailService {
    List<OrderDetail> getOrderDetailsByOrderId(long id);
    List<OrderDetail> getList();

}
