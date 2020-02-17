package com.example.demo.util;

import com.example.demo.entity.Picture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PictureUtil {

    @Value("${PATH}")
    private String path;
    @Value("${IP}")
    private String IP;

    String suffix;
    String newName;

    public Picture downloadPicture(MultipartFile file, String type, Integer DId,Integer peopleId){
        String[] paths = (path+type+"/").split("/");
        StringBuffer fullPath = new StringBuffer();
        for(int i = 0;i < paths.length;i++){
            fullPath.append((paths[i])).append("/");
            File makeDir = new File(fullPath.toString());
            if(!makeDir.exists()){
                makeDir.mkdir();
            }
        }

        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(
                    new FileOutputStream(path+type+"/" + file.getOriginalFilename()));
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //用当前时间重命名文件
        String fileName = file.getOriginalFilename();
        suffix = fileName.substring(fileName.lastIndexOf("."));
        File oldFile = new File(path+type+"/" + file.getOriginalFilename());
        Date now = new Date();
        //System.out.println(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");//修改日期格式
        //System.out.println(dateFormat.format(now));
        newName = dateFormat.format(now);
        //System.out.println(newName);
        File newFile = new File(path+type+"/" + newName + suffix);
        oldFile.renameTo(newFile);


        String url = IP + type + "/" + newName + suffix;

        Picture picture = new Picture();
        picture.setDId(DId);
        picture.setPeopleId(peopleId);
        picture.setPictureUrl(url);
        picture.setPicturePosition(path+type+"/" + newName + suffix);
        return picture;
    }


    public void deletePicture(Picture picture){
        File deleteFile = new File(picture.getPicturePosition());
        deleteFile.delete();
        System.out.println(picture.getPicturePosition()+"  Successfully Deletion!");
    }
}
