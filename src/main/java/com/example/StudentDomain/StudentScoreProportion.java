package com.example.StudentDomain;


public class StudentScoreProportion {

  private String courseId;
  private long usualProportion;
  private long midTermProportion;
  private long finalTermProportion;
  private long extraProportion;


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public long getUsualProportion() {
    return usualProportion;
  }

  public void setUsualProportion(long usualProportion) {
    this.usualProportion = usualProportion;
  }


  public long getMidTermProportion() {
    return midTermProportion;
  }

  public void setMidTermProportion(long midTermProportion) {
    this.midTermProportion = midTermProportion;
  }


  public long getFinalTermProportion() {
    return finalTermProportion;
  }

  public void setFinalTermProportion(long finalTermProportion) {
    this.finalTermProportion = finalTermProportion;
  }


  public long getExtraProportion() {
    return extraProportion;
  }

  public void setExtraProportion(long extraProportion) {
    this.extraProportion = extraProportion;
  }

}
