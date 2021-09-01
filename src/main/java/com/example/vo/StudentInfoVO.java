package com.example.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentInfoVO implements Serializable {

    private String studentId;
    private String studentName;
    private String gender;
    private Date birthday;
    private String classId;
    private String collegeName;
    private String majorName;
    private Date admissionDate;
    private String schoolingPeriod;
    private String studentStatus;

    private static final long serialVersionUID = 1L;
}
