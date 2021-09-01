package com.example.util;

import com.example.entity.StudentInfo;
import com.example.vo.StudentInfoVO;

public class StudentInfo_to_StudentInfoVO {
    public static StudentInfoVO convert(StudentInfo studentInfo,String gender, String collegeName, String majorName)
    {
        StudentInfoVO vo=new StudentInfoVO();
        vo.setStudentId(studentInfo.getStudentId());
        vo.setStudentName(studentInfo.getStudentName());
        vo.setBirthday(studentInfo.getBirthday());
        vo.setClassId(studentInfo.getClassId());
        vo.setCollegeName(collegeName);
        vo.setMajorName(majorName);
        vo.setAdmissionDate(studentInfo.getAdmissionDate());
        vo.setSchoolingPeriod(studentInfo.getStudentId());
        vo.setStudentStatus(studentInfo.getStudentStatus());
        return vo;
    }
}
