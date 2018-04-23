package com.diit.core.user.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 332112317784402898L;

	private Long id;

    private String avatar;
    
    private String account;

    private String password;

    private String name;
    
    private Date birthday;
    
    private Short sex;

    private String email;

    private String phone;
    
    private Short status;

    private Date createtime;
    
    private String remark;

}