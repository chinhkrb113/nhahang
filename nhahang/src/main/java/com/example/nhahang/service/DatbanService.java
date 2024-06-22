package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.Blog;
import com.example.nhahang.entity.Datban;
import com.example.nhahang.model.request.CreateBlogRequest;
import com.example.nhahang.model.request.CreateDatbanRequest;

public interface DatbanService {
	List<Datban> getList();  // lấy tát cả
	Datban getDatban(long id);//lay id
	Datban createDatban(CreateDatbanRequest request);//tạo
	Datban updateDatban(long id,CreateDatbanRequest request);//sửa

    void deleteDatban(long id);//xóa

}
