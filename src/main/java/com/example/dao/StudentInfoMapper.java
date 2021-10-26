package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.StudentInfo;
import com.example.entity.StudentVo;
import com.example.vo.StudentInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.dao.StudentInfo
 */
@Mapper
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {
   //查找所有学生
   @Deprecated
   List<StudentInfoVO> searchAllStudentInfoVO();

   //修改学生信息
   void updateStudent(@Param("vo") StudentVo vo);

   //查找所有学生id
   List<String> searchAllStudentId();

   //查找所有学生姓名
   List<String> searchAllStudentName();

}




