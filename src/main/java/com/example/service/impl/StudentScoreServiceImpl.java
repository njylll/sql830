package com.example.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CourseInfoMapper;
import com.example.dao.StudentScoreMapper;
import com.example.dao.StudentVoMapper;
import com.example.entity.CourseInfo;
import com.example.entity.StudentScore;
import com.example.entity.StudentVo;
import com.example.service.StudentScoreService;
import com.example.util.UUIDGenerator;
import com.example.util.uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentScoreServiceImpl extends ServiceImpl<StudentScoreMapper, StudentScore> implements StudentScoreService {
    @Autowired
    private StudentScoreMapper studentScoreMapper;

    /**
     *添加
     * */
    public void add(StudentScore studentScore)
    {
        studentScoreMapper.insert(studentScore);
    }


    public void update(StudentScore studentScore) {
        QueryWrapper<StudentScore> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",studentScore.getStudentId());
        queryWrapper.eq("course_id",studentScore.getCourseId());
        studentScoreMapper.update(studentScore,queryWrapper);
    }


    public List<StudentScore> listAll()
    {
        return studentScoreMapper.listAll();
    }


}