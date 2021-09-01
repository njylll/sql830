package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.StudentVo;
import com.example.service.StudentVoService;
import com.example.dao.StudentVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StudentVoServiceImpl extends ServiceImpl<StudentVoMapper, StudentVo>
    implements StudentVoService{

    @Autowired
    private StudentVoMapper voMapper;

    @Override
    public void insertStudent(StudentVo vo, String uuid) {
        voMapper.insertStudentInfo(vo,uuid);
    }
}




