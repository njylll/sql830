package com.example.StudentDAO;

import com.example.StudentDomain.CourseInfo;
import com.example.StudentDomain.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseInfoDAO extends JpaRepository<CourseInfo, Integer> {
    @Query(nativeQuery = true,value ="select * from course_info where class_teacher like binary concat('','%',?1,'%','') or class_name like binary concat('','%',?1,'%','')" +
            "or classroom like binary concat('','%',?1,'%','')")

    List<CourseInfo> findAllClaByChinese(String queryText);
//    @Param("searchContent")
    @Query(nativeQuery = true,value ="select * from class_info where   class_id =?1 or class_duration = ?1 or class_createTime like concat('','%',?1,'%','') " +
            "or class_updateTime like  concat('','%',?1,'%','')")
    List<CourseInfo> findAllClaByNumOrLet(String queryText);
    @Query(nativeQuery = true,value = "select * from class_info where classroom=?1")
    List<CourseInfo> findAllClaByRoom(String queryText);

}
