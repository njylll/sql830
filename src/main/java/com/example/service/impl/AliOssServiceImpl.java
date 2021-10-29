package com.example.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.example.service.AliOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class AliOssServiceImpl implements AliOssService {

    private static final String bucketName="sunnynoodlebucket";

    @Autowired
    private OSS ossClient;

    //上传文件
    @Override
    public void uploadFile(String filePath,String file) {
        ossClient.putObject(bucketName, filePath, new ByteArrayInputStream(file.getBytes()));

    }

    //下载文件
    @Override
    public String downloadFile(String filePath)
        throws IOException
    {
        OSSObject ossObject = ossClient.getObject(bucketName, filePath);
        InputStream content = ossObject.getObjectContent();
        StringBuilder stringBuilder=new StringBuilder();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                stringBuilder.append(line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            content.close();
        }
        return stringBuilder.toString();
    }

    @Override
    public void uploadImg(String filePath, InputStream inputStream) {
        ossClient.putObject(bucketName, filePath, inputStream);
    }

    //删除文件
    @Override
    public void deleteFile(String filePath) {
        ossClient.deleteObject(bucketName, filePath);
    }
}
