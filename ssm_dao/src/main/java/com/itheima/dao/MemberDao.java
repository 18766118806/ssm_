package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;


/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/3 18:41
 **/

public interface MemberDao {
    @Select ("select * from member where id = #{mid}")
    Member findById(String mid);
}
