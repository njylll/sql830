package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.AdminAccount;
import com.example.vo.AdminAccountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.dao.AdminAccount
 */
@Mapper
public interface AdminAccountMapper extends BaseMapper<AdminAccount> {


    int add(AdminAccountVO user);

    AdminAccountVO queryById(@Param("id") String id);

    List<AdminAccountVO> queryByOne(@Param("username") String username, @Param("password") String password);

    List<AdminAccountVO> queryByAll();

    int update(AdminAccountVO adminaccountvo);
}




