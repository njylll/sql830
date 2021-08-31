package com.example.StudentDomain;


public class StudentInfo {

  private String uuid;
  private String studentId;
  private String studentName;
  private String gender;
  private java.sql.Date birthday;
  private String classId;
  private String collegeId;
  private String majorId;
  private java.sql.Date admissionDate;
  private long schoolingPeriod;
  private String studentStatus;


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public java.sql.Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Date birthday) {
    this.birthday = birthday;
  }


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }


  public String getMajorId() {
    return majorId;
  }

  public void setMajorId(String majorId) {
    this.majorId = majorId;
  }


  public java.sql.Date getAdmissionDate() {
    return admissionDate;
  }

  public void setAdmissionDate(java.sql.Date admissionDate) {
    this.admissionDate = admissionDate;
  }


  public long getSchoolingPeriod() {
    return schoolingPeriod;
  }

  public void setSchoolingPeriod(long schoolingPeriod) {
    this.schoolingPeriod = schoolingPeriod;
  }


  public String getStudentStatus() {
    return studentStatus;
  }

  public void setStudentStatus(String studentStatus) {
    this.studentStatus = studentStatus;
  }

}
