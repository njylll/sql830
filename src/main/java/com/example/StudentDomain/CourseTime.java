package com.example.StudentDomain;


public class CourseTime {

  private String courseId;
  private long startWeek;
  private long endWeek;
  private String dayTime;
  private String section;


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public long getStartWeek() {
    return startWeek;
  }

  public void setStartWeek(long startWeek) {
    this.startWeek = startWeek;
  }


  public long getEndWeek() {
    return endWeek;
  }

  public void setEndWeek(long endWeek) {
    this.endWeek = endWeek;
  }


  public String getDayTime() {
    return dayTime;
  }

  public void setDayTime(String dayTime) {
    this.dayTime = dayTime;
  }


  public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }

}
