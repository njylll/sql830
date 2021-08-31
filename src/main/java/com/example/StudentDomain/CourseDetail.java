package com.example.StudentDomain;


public class CourseDetail {

  private String courseId;
  private String startSchoolYear;
  private String endSchoolYear;
  private long startTerm;
  private String courseCondition;
  private String teacherName;
  private String teachingLocation;


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public String getStartSchoolYear() {
    return startSchoolYear;
  }

  public void setStartSchoolYear(String startSchoolYear) {
    this.startSchoolYear = startSchoolYear;
  }


  public String getEndSchoolYear() {
    return endSchoolYear;
  }

  public void setEndSchoolYear(String endSchoolYear) {
    this.endSchoolYear = endSchoolYear;
  }


  public long getStartTerm() {
    return startTerm;
  }

  public void setStartTerm(long startTerm) {
    this.startTerm = startTerm;
  }


  public String getCourseCondition() {
    return courseCondition;
  }

  public void setCourseCondition(String courseCondition) {
    this.courseCondition = courseCondition;
  }


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }


  public String getTeachingLocation() {
    return teachingLocation;
  }

  public void setTeachingLocation(String teachingLocation) {
    this.teachingLocation = teachingLocation;
  }

}
