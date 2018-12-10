package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/8 20:01
 **/
public interface SysLogDao {

    @Insert ("insert into syslog (visitTime,username,ip,url,executionTime,method) values (#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save (SysLog sysLog);
}
