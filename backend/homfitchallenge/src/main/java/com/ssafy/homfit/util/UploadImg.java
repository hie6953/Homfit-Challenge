package com.ssafy.homfit.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class UploadImg {
    public static String writeImg(MultipartFile file){
        String filePath = null;
        try {
            String originalFileName = file.getOriginalFilename();
            String fileName = new MD5Generator(originalFileName).toString();
            String savePath = System.getProperty("user.dir") + "\\files";
            if(!new File(savePath).exists()){
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            filePath = savePath + "\\" + fileName;
            file.transferTo(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return filePath;
    }
}
