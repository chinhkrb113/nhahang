package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.Productcolor;
import com.example.nhahang.model.request.CreateProductcolorRequest;

public interface ProductcolorService {

	List<Productcolor> getList();  // lấy hết
	List<Productcolor> getListcolorByUser(long id); // láy theo user
	Productcolor createProductcolor(CreateProductcolorRequest request);//tạo mới
	Productcolor updateProductcolor(long id, CreateProductcolorRequest request); //sửa
	 void deleteProductcolor(long id);  //xóa
	
	
}
