package com.pvt.dogpark.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wastebin")
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class WasteBinDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String color;
	private Double latitude;
	private Double longitude;

}
