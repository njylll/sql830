package com.example.service;

import com.example.entity.StudentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.StudentVo;
import com.example.vo.StudentInfoVO;

import java.util.List;

/**
 *
 */
public interface StudentInfoService extends IService<StudentInfo> {

    //查找所有学生信息，VO的形式返回
    List<StudentInfoVO> findAllStudentInfoVO();

    //更新学生信息
    void updateStudentInfo(StudentVo vo);

    //查询所有学生id
    List<String> findAllStudentId();

    //查询所有学生姓名
    List<String> findAllStudentName();

}
