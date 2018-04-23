package com.diit.web.user.vo;

import java.text.SimpleDateFormat;

import com.diit.core.user.pojo.User;


/**
 * 后台用户展示VO
 *
 * @author lishw
 * @create 2017-01-18
 */
public class ManageUserVO extends User {

    private String joinus;
    
    public String getJoinus() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy MM");

        return format.format(this.getCreatetime()).toString() + "加入公司";
    }


}
