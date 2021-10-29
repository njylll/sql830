package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.StudentScoreProportion;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    void updateIdOrName(@Param("uUid") String uuid,@Param("uName")String username,@Param("uId")String id);
}


