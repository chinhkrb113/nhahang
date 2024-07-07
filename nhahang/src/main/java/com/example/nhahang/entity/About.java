package com.example.nhahang.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "about")
public class About { // Ánh xạ tới bản about
	@Id
	private String id;
	private String title;
	private String noidung;
	@ManyToOne
    @JoinColumn(name="image_id")
    private Image image;
}
