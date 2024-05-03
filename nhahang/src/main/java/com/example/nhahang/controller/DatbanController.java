package com.example.nhahang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nhahang.entity.Datban;
import com.example.nhahang.model.request.CreateDatbanRequest;
import com.example.nhahang.model.response.MessageResponse;
import com.example.nhahang.service.DatbanService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/datban")
@CrossOrigin(origins = "*",maxAge = 3600)
public class DatbanController {
	@Autowired
    private DatbanService datbanService;

    @GetMapping("/")
    @Operation(summary="Lấy tất cả danh sách blog")
    public ResponseEntity<List<Datban>> getList(){
        List<Datban> list = datbanService.getList();

        return ResponseEntity.ok(list);

    }
    @GetMapping("/{id}")
    @Operation(summary="Lấy ra blog bằng ID")
    public ResponseEntity<Datban> getDatban(@PathVariable long id){
        
    	Datban datban =datbanService.getDatban(id);
        return ResponseEntity.ok(datban);

    }

    @PostMapping("/create")
    @Operation(summary="Tạo mới blog")
    public ResponseEntity<Datban> create(@RequestBody CreateDatbanRequest request){

    	Datban datban = datbanService.createDatban(request);

        return ResponseEntity.ok(datban);

    }

    @PutMapping("/update/{id}")
    @Operation(summary="Tìm blog bằng id và cập nhật blog đó")
    public ResponseEntity<Datban> update(@PathVariable long id, @RequestBody CreateDatbanRequest request){

    	Datban datban = datbanService.updateDatban(id, request);

        return ResponseEntity.ok(datban);

    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary="Xóa blog bằng Id")
    public ResponseEntity<?> delete(@PathVariable long id){
        datbanService.deleteDatban(id);
        return ResponseEntity.ok(new MessageResponse("Delete success"));
    }
    
}
