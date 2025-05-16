package com.iflytek.ylao.common.loginpojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginPojo {
    private String username;
    private String password;
    private String captcha;
    private Integer power;
}
