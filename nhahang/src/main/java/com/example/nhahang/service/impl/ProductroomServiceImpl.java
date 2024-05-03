package com.example.nhahang.service.impl;


import java.util.List;
import org.springframework.stereotype.Service;

import com.example.nhahang.entity.Productroom;
import com.example.nhahang.entity.User;
import com.example.nhahang.exception.NotFoundException;
import com.example.nhahang.model.request.CreateProductroomRequest;
import com.example.nhahang.repository.ProductroomRepository;
import com.example.nhahang.repository.UserRepository;
import com.example.nhahang.service.ProductroomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
@Service
public class ProductroomServiceImpl implements ProductroomService {

    @Autowired
    private ProductroomRepository productroomRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    // tất cả
    public List<Productroom> getList() {
        return productroomRepository.findAll(Sort.by("id").descending());
    }
    // theo user
    @Override
    public List<Productroom> getListroomByUser(long id){
        List<Productroom> list =productroomRepository.getListRoomByUser(id);
        return list;
    }
    //
    @Override
    public Productroom createProductroom(CreateProductroomRequest request) {
    	Productroom productroom = new Productroom();
    	productroom.setName(request.getName());
    	User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new NotFoundException("Not Found User"));
    	productroom.setUser(user);
    	productroomRepository.save(productroom);
        return productroom;
    }
    @Override
    public Productroom updateProductroom(long id, CreateProductroomRequest request) {
        // TODO Auto-generated method stub

    	Productroom productroom = productroomRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Foud Tag"));
    	productroom.setName(request.getName());
        productroomRepository.save(productroom);
        return productroom;
    }

    @Override
    public void deleteProductroom(long id) {
        // TODO Auto-generated method stub
    	Productroom productroom = productroomRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Foud Tag"));
    	productroomRepository.delete(productroom);
    }

}
