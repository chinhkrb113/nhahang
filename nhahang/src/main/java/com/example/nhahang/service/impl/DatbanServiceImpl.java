package com.example.nhahang.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.nhahang.entity.Datban;
import com.example.nhahang.entity.Image;
import com.example.nhahang.entity.Tag;
import com.example.nhahang.entity.User;
import com.example.nhahang.exception.NotFoundException;
import com.example.nhahang.model.request.CreateDatbanRequest;
import com.example.nhahang.repository.DatbanRepository;
import com.example.nhahang.repository.ImageRepository;
import com.example.nhahang.repository.TagRepository;
import com.example.nhahang.repository.UserRepository;
import com.example.nhahang.service.DatbanService;
import com.example.nhahang.util.EmailUtil;

@Service
public class DatbanServiceImpl implements DatbanService {

    @Autowired
    private DatbanRepository datbanRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public List<Datban> getList() {
        // TODO Auto-generated method stub
        return datbanRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Datban getDatban(long id){
    	Datban datban = datbanRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Datban"));
        return datban;
    }
    
    @Override
    public Datban createDatban(CreateDatbanRequest request) {
        // TODO Auto-generated method stub
    	Datban datban = new Datban();
    	datban.setNguoilon(request.getNguoilon());
    	datban.setTreem(request.getTreem());
    	datban.setGioden(request.getGioden());
    	datban.setGmail(request.getGmail());
    	datban.setSdt(request.getSdt());
    	datban.setTen(request.getTen());
    	datban.setGhichu(request.getGhichu());
    	datban.setTreem(request.getTreem());
    	Date ngayden = request.getNgayden();
	   
	    datban.setNgayden(ngayden);
      
       datbanRepository.save(datban);
        return datban;
    }

    @Override
    public Datban updateDatban(long id, CreateDatbanRequest request) {
        // TODO Auto-generated method stub
    	Datban datban = datbanRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
       
    	datban.setNguoilon(request.getNguoilon());
    	datban.setTreem(request.getTreem());
    	datban.setGioden(request.getGioden());
    	datban.setGmail(request.getGmail());
    	datban.setSdt(request.getSdt());
    	datban.setTen(request.getTen());
    	datban.setGhichu(request.getGhichu());
    	datban.setTreem(request.getTreem());
    	Date ngayden = request.getNgayden();
	   
	    datban.setNgayden(ngayden);
      
       datbanRepository.save(datban);
        return datban;
    }

    @Override
    public void deleteDatban(long id) {
        // TODO Auto-generated method stub
    	Datban datban = datbanRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));

            try {
            emailUtil.sendCancel(datban.getGmail(), "Nhân viên đã huỷ đặt bàn, vui lòng liên hệ nhà hàng để biết thêm chi tiết.");
            } catch (MessagingException e) {
          throw new RuntimeException("please try again");
        }
    
     
       datbanRepository.delete(datban);
    }


}
