package com.diit.core.role.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "DIIT_ROLE")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1974211776684848898L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private Long num;
    
    private String name;

    private Date createtime;
    
    private String remark;

}