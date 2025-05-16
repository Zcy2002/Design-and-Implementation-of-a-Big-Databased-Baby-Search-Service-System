package com.iflytek.ylao.common.loginpojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPojo {
    private String username;
    private String password;
    private String login;
    private String sex;
    private long tel;
    private Integer power;
}
