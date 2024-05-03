package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.Productroom;
import com.example.nhahang.model.request.CreateProductroomRequest;

public interface ProductroomService {
	List<Productroom> getList();  // lấy hết
	List<Productroom> getListroomByUser(long id); // láy theo user
	Productroom createProductroom(CreateProductroomRequest request);//tạo mới
	Productroom updateProductroom(long id, CreateProductroomRequest request); //sửa
	 void deleteProductroom(long id);  //xóa

}
