package com.example.nhahang.service;
import java.util.List;

import com.example.nhahang.entity.Productsize;
import com.example.nhahang.model.request.CreateProductsizeRequest;
public interface ProductsizeService {
	List<Productsize> getList();  // lấy hết
	List<Productsize> getListsizeByUser(long id); // láy theo user
	Productsize createProductsize(CreateProductsizeRequest request);//tạo mới
	Productsize updateProductsize(long id, CreateProductsizeRequest request); //sửa
	 void deleteProductsize(long id);  //xóa

}
