package com.example.nhahang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.nhahang.entity.Role;
import com.example.nhahang.repository.RoleRepository;
import com.example.nhahang.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
    private RoleRepository roleRepository;
	
	 @Override
	    public List<Role> getListrole() {
	        // TODO Auto-generated method stub
	        return roleRepository.findAll(Sort.by("id").descending());
	    }
}
