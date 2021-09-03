package com.example.util;
import java.util.UUID;

public class UUIDGenerator {
    public static String generateStudentUUID(String studentId)
    {
        UUID uuid = UUID.nameUUIDFromBytes(studentId.getBytes());
        return uuid.toString();
    }

}
