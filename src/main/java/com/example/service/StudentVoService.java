package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.StudentVo;

/**
 *
 */
public interface StudentVoService extends IService<StudentVo> {
    //增加学生
    void insertStudent(StudentVo vo,String UUID);

}
