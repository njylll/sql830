package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName course_vo
 */
@TableName(value ="course_vo")
public class CourseVo implements Serializable {
    /**
     * 课程号
     */
    private String courseId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 
     */
    private String teacherName;

    /**
     * 授课地点
     */
    private String teachingLocation;

    /**
     * 考核方式
     */
    private String assessmentMethod;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 课程号
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * 课程号
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * 课程名
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 课程名
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * 授课地点
     */
    public String getTeachingLocation() {
        return teachingLocation;
    }

    /**
     * 授课地点
     */
    public void setTeachingLocation(String teachingLocation) {
        this.teachingLocation = teachingLocation;
    }

    /**
     * 考核方式
     */
    public String getAssessmentMethod() {
        return assessmentMethod;
    }

    /**
     * 考核方式
     */
    public void setAssessmentMethod(String assessmentMethod) {
        this.assessmentMethod = assessmentMethod;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CourseVo other = (CourseVo) that;
        return (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getTeacherName() == null ? other.getTeacherName() == null : this.getTeacherName().equals(other.getTeacherName()))
            && (this.getTeachingLocation() == null ? other.getTeachingLocation() == null : this.getTeachingLocation().equals(other.getTeachingLocation()))
            && (this.getAssessmentMethod() == null ? other.getAssessmentMethod() == null : this.getAssessmentMethod().equals(other.getAssessmentMethod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getTeacherName() == null) ? 0 : getTeacherName().hashCode());
        result = prime * result + ((getTeachingLocation() == null) ? 0 : getTeachingLocation().hashCode());
        result = prime * result + ((getAssessmentMethod() == null) ? 0 : getAssessmentMethod().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseId=").append(courseId);
        sb.append(", courseName=").append(courseName);
        sb.append(", teacherName=").append(teacherName);
        sb.append(", teachingLocation=").append(teachingLocation);
        sb.append(", assessmentMethod=").append(assessmentMethod);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}