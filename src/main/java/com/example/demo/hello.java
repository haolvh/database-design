package com.example.demo;

import com.example.demo.entity.BallTeam;
import com.example.demo.entity.Department;
import com.example.demo.entity.Picture;
import com.example.demo.util.PictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class hello {

    @Autowired
    private PictureUtil pictureUtil;

    Map<String,Object> map = new HashMap<>();

    @ResponseBody
    @RequestMapping("/api/hello")
    public String showHtml() {
        return "/member.html";
    }
}
