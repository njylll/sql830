package com.example.StudentDomain;


public class StudentScore {

  private String studentId;
  private String courseId;
  private double usualScore;
  private double midTermScore;
  private double finalTermScore;


  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public double getUsualScore() {
    return usualScore;
  }

  public void setUsualScore(double usualScore) {
    this.usualScore = usualScore;
  }


  public double getMidTermScore() {
    return midTermScore;
  }

  public void setMidTermScore(double midTermScore) {
    this.midTermScore = midTermScore;
  }


  public double getFinalTermScore() {
    return finalTermScore;
  }

  public void setFinalTermScore(double finalTermScore) {
    this.finalTermScore = finalTermScore;
  }

}
