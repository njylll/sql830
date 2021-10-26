package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.StudentInfoMapper;
import com.example.entity.StudentInfo;
import com.example.entity.StudentVo;
import com.example.service.StudentInfoService;
import com.example.vo.StudentInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo>
    implements StudentInfoService{
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    public List<StudentInfoVO> findAllStudentInfoVO() {
        return studentInfoMapper.searchAllStudentInfoVO();
    }

    @Override
    public void updateStudentInfo(StudentVo vo) {
        studentInfoMapper.updateStudent(vo);
    }

    @Override
    public List<String> findAllStudentId() {
        return studentInfoMapper.searchAllStudentId();
    }

    @Override
    public List<String> findAllStudentName() {
        return studentInfoMapper.searchAllStudentName();
    }
}




