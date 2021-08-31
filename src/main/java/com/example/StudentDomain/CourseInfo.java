package com.example.StudentDomain;


public class CourseInfo {

  private String uuid;
  private String courseId;
  private String courseName;
  private String responsibleCollegeId;
  private long creditHours;
  private double credit;
  private String courseType;
  private String courseType2;
  private String teachingWay;
  private String assessmentMethod;


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  public String getResponsibleCollegeId() {
    return responsibleCollegeId;
  }

  public void setResponsibleCollegeId(String responsibleCollegeId) {
    this.responsibleCollegeId = responsibleCollegeId;
  }


  public long getCreditHours() {
    return creditHours;
  }

  public void setCreditHours(long creditHours) {
    this.creditHours = creditHours;
  }


  public double getCredit() {
    return credit;
  }

  public void setCredit(double credit) {
    this.credit = credit;
  }


  public String getCourseType() {
    return courseType;
  }

  public void setCourseType(String courseType) {
    this.courseType = courseType;
  }


  public String getCourseType2() {
    return courseType2;
  }

  public void setCourseType2(String courseType2) {
    this.courseType2 = courseType2;
  }


  public String getTeachingWay() {
    return teachingWay;
  }

  public void setTeachingWay(String teachingWay) {
    this.teachingWay = teachingWay;
  }


  public String getAssessmentMethod() {
    return assessmentMethod;
  }

  public void setAssessmentMethod(String assessmentMethod) {
    this.assessmentMethod = assessmentMethod;
  }

}
