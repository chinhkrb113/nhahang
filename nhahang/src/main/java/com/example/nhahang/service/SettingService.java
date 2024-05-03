package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.Setting;
import com.example.nhahang.model.request.CreateSettingRequest;

public interface SettingService {
	List<Setting> getListSetting();
	Setting updateSetting(CreateSettingRequest request);
	
}
