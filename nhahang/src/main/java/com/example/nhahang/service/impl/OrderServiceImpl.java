package com.example.nhahang.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nhahang.entity.Category;
import com.example.nhahang.entity.Image;
import com.example.nhahang.entity.Order;
import com.example.nhahang.entity.OrderDetail;
import com.example.nhahang.entity.Orderstatus;
import com.example.nhahang.entity.Product;
import com.example.nhahang.entity.User;
import com.example.nhahang.entity.Voucher;
import com.example.nhahang.exception.NotFoundException;
import com.example.nhahang.model.request.CreateOrderDetailRequest;
import com.example.nhahang.model.request.CreateOrderRequest;
import com.example.nhahang.model.request.CreateProductRequest;
import com.example.nhahang.repository.CategoryRepository;
import com.example.nhahang.repository.OrderDetailRepository;
import com.example.nhahang.repository.OrderRepository;
import com.example.nhahang.repository.OrderstatusRepository;
import com.example.nhahang.repository.UserRepository;
import com.example.nhahang.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderstatusRepository orderstatusRepository;
    
    @Override
    public void placeOrder(CreateOrderRequest request) {
        // TODO Auto-generated method stub
        Order order = new Order();
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("Not Found User With Username:" + request.getUsername()));
        order.setFirstname(request.getFirstname());
        order.setLastname(request.getLastname());
        order.setCountry(request.getCountry());
        order.setAddress(request.getAddress());
        order.setTown(request.getTown());
        order.setState(request.getState());
        order.setPostCode(request.getPostCode());
        order.setEmail(request.getEmail());
        order.setPhone(request.getPhone());
        order.setTotalPrice(request.getTotalPrice());
        order.setNote(request.getNote());   
        order.setSale(request.getSale());
        order.setOrderCode(request.getOrderCode());
        order.setCreateAt(new Timestamp(System.currentTimeMillis()));
        order.setOrderState(0);
        Orderstatus orderstatus = orderstatusRepository.findById(request.getStatus()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getStatus()));
    	order.setOrderstatus(orderstatus);  
        order.setBank(request.getBank());
        orderRepository.save(order);
        for(CreateOrderDetailRequest rq: request.getOrderDetails()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(rq.getName());
            // orderDetail.setColor(rq.getColor());
            orderDetail.setSize(rq.getSize());
            orderDetail.setRoom(rq.getRoom());
            orderDetail.setPrice(rq.getPrice());
            orderDetail.setSoluong(rq.getSoluong());
            orderDetail.setSubTotal(rq.getPrice()* rq.getSoluong());
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);            
        }
        order.setUser(user);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getList() {
        return orderRepository.findAll(Sort.by("id").descending());
    }
    @Override
    public List<Orderstatus> getListstatus() {
        return orderstatusRepository.findAll(Sort.by("id").descending());
    }
    @Override
    public List<Order> getOrderByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Not Found User With Username:" + username));

        List<Order> orders = orderRepository.getOrderByUser(user.getId());
        return orders;  
    }

    @Override
    public ResponseEntity<?> checkOrder(String code) {
        Order order = orderRepository.findByOrderCode(code).orElseThrow();
        order.setOrderState(1);
        orderRepository.save(order);
        return ResponseEntity.ok(new HashMap<>());
    }

    @Override
    public ResponseEntity<?> removeOrder(String code) {
        return ResponseEntity.ok(new HashMap<>());
    }
    @Override
    public Order updateOrder(long id, CreateOrderRequest request) {
    	
    	// TODO Auto-generated method stub
    	Order order= orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
    	
    	Orderstatus orderstatus = orderstatusRepository.findById(request.getStatus()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getStatus()));
    	order.setOrderstatus(orderstatus);  
    	orderRepository.save(order);

        return order;
    }
    @Override
    public List<Order> getListOrder(long id) {
	 List<Order> list = orderRepository.getListOrder(id);
        return list;
    }
}
