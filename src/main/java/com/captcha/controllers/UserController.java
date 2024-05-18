package com.captcha.controllers;

import cn.apiclub.captcha.Captcha;
import com.captcha.model.User;
import com.captcha.services.UserService;
import com.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("register")
    public User registerUser(){
        User user=new User();
        getCaptcha(user);
        return user;
    }

    private void getCaptcha(User user) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        user.setHiddenCaptcha(captcha.getAnswer());
        user.setCaptcha("");
        user.setUserCaptcha(CaptchaUtil.encodeCaptcha(captcha));
    }

    @PostMapping("save")
    public String createUser(@RequestBody User user){
        String response="";
        if(user==null){
            return "User could not be null";
        } else if(user.getCaptcha().equals(user.getHiddenCaptcha())){
            response=userService.createUser(user);
        }else {
            response="Invalid captcha";
        }
        return response;
    }


}
