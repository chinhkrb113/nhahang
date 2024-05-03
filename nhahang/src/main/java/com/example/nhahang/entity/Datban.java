package com.example.nhahang.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="datban")
public class Datban {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private int nguoilon;
	private int treem;
	private Date ngayden;
	private String gioden;
	private String gmail;
	private String sdt;
	private String ten;
	private String ghichu;
	
	

}
