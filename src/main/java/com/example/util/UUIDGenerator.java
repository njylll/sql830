package com.example.util;
import java.util.UUID;

public class UUIDGenerator {
    public static String generateStudentUUID(String studentId)
    {
        UUID uuid = UUID.nameUUIDFromBytes(studentId.getBytes());
        return uuid.toString();
    }

    public static String generateCourseUUID(String courseId)
    {
        UUID uuid = UUID.nameUUIDFromBytes(courseId.getBytes());
        return uuid.toString();
    }

    public static String generateCourseDetailUUID(String courseId)
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
