package com.example.nhahang.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.nhahang.entity.Order;
import com.example.nhahang.entity.Orderstatus;
import com.example.nhahang.entity.Voucher;
import com.example.nhahang.model.request.CreateOrderRequest;

public interface OrderService {
    
    void placeOrder(CreateOrderRequest request);

    List<Order> getList();
    
    List<Order> getOrderByUser(String username);
    
    ResponseEntity<?> checkOrder(String code);

    ResponseEntity<?> removeOrder(String code);
    // update 
    Order updateOrder(long id, CreateOrderRequest request);
    List<Orderstatus> getListstatus();
    
    List<Order> getListOrder(long id);
}
