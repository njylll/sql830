package com.example.service;

import java.io.IOException;
import java.io.InputStream;

public interface AliOssService {
    void uploadFile(String filePath, String file);

    String downloadFile(String filePath) throws IOException;

    void uploadImg(String filePath, InputStream inputStream);

    void deleteFile(String filePath);



}
