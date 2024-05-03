package com.example.nhahang.model.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDatbanRequest {
	
	private int nguoilon;
	private int treem;
	private Date ngayden;
	private String gioden;
	private String gmail;
	private String sdt;
	private String ten;
	private String ghichu;
}
