package com.captcha.utils;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class CaptchaUtil {
    //create captcha
    public static Captcha createCaptcha(int width,int height){
        return new Captcha.Builder(width,height).addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer(),new DefaultWordRenderer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();
    }

    //encode captcha into String
    public static String encodeCaptcha(Captcha captcha){
        String captchaInStr=null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(),"jpg",bos);
            byte[] encode = Base64.getEncoder().encode(bos.toByteArray());
            captchaInStr=new String(encode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return captchaInStr;
    }
}
