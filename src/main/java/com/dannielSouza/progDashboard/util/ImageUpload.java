package com.dannielSouza.progDashboard.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ImageUpload {
  public static Boolean imageUploader(MultipartFile image){
    Boolean uploadStatus = false;

    if(!image.isEmpty()){
      String fileName = image.getOriginalFilename();
      try{
        String directoryFilename = "C:\\Users\\danfe\\Documents\\projectDashboard\\progDashboard\\server\\src\\main\\resources\\static\\images";
        File dir = new File(directoryFilename);
        if(!dir.exists()){
          dir.mkdirs();
        }
        File file = new File(dir.getAbsolutePath()+ File.separator + fileName);
        BufferedOutputStream Stream = new BufferedOutputStream(new FileOutputStream(file));
        Stream.write(image.getBytes());
        Stream.close();

        System.out.println("Armazenado em: " + file.getAbsolutePath());
        System.out.println("Upload do arquivo: " + fileName + " com sucesso.");

      }catch (Exception e){
        new RuntimeException(e);
      }
    }else{
      new RuntimeException("Imagem vázia.");
    }
    return uploadStatus;
  }
}
