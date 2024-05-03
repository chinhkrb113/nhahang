package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.About;
import com.example.nhahang.model.request.CreateAboutRequest;

public interface AboutService {
	List<About> getListAbout();
	About updateAbout(CreateAboutRequest request);
}
