package com.iflytek.ylao.common.captcha;


import com.wf.captcha.GifCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        // 动态英文和数字验证码
        GifCaptcha gifCaptcha = new GifCaptcha(120,40,4);

        String captCode = gifCaptcha.text();
        request.getSession().setAttribute("captCode",captCode);

        gifCaptcha.out(outputStream);
    }
}
