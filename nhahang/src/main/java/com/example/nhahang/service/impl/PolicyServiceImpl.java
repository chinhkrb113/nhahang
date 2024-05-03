package com.example.nhahang.service.impl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.nhahang.entity.Category;
import com.example.nhahang.entity.Image;
import com.example.nhahang.entity.Policy;
import com.example.nhahang.entity.Tag;
import com.example.nhahang.entity.User;
import com.example.nhahang.exception.NotFoundException;
import com.example.nhahang.model.request.CreatePolicyRequest;
import com.example.nhahang.repository.BlogRepository;
import com.example.nhahang.repository.ImageRepository;
import com.example.nhahang.repository.PolicyRepository;
import com.example.nhahang.repository.TagRepository;
import com.example.nhahang.repository.UserRepository;
import com.example.nhahang.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override     // lấy all
    public List<Policy> getList() {
        return policyRepository.findAll(Sort.by("id").descending());
    }

    @Override    // láy theo id
    public Policy getPolicy(long id){
    	Policy policy = policyRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Policy"));
        return policy;
    }
    @Override   // lấy theo kiểu
    public List<Policy> getListPolicyBykieu(long id){
        List<Policy> list =policyRepository.getListPolicyBykieu(id);
        return list;
    }
    @Override   // tạo mới
    public Policy createPolicy(CreatePolicyRequest request) {
        // TODO Auto-generated method stub
    	Policy policy = new Policy();
    	policy.setTitle(request.getTitle());
    	policy.setContent(request.getContent());
    	policy.setKieu(request.getKieu());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        policy.setImage(image);
        policy.setEnable(false);
        policyRepository.save(policy);
        return policy;
    }

    @Override  // update
    public Policy updatePolicy(long id, CreatePolicyRequest request) {
        // TODO Auto-generated method stub
    	Policy policy = policyRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Policy"));
    	policy.setTitle(request.getTitle());
    	policy.setContent(request.getContent());
    	policy.setKieu(request.getKieu());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        policy.setImage(image);
        
        policyRepository.save(policy);
        return policy;
    }

    @Override
    public void deletePolicy(long id) {
        // TODO Auto-generated method stub
    	Policy policy = policyRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Policy"));
        policyRepository.delete(policy);
    }
    @Override
    public void enablePolicy(long id) {
        // TODO Auto-generated method stub
    	Policy policy = policyRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Policy With Id: " + id));
        if(policy.isEnable()){
        	policy.setEnable(false);
        } else{
        	policy.setEnable(true);
        }
        policyRepository.save(policy);
    }

    


}