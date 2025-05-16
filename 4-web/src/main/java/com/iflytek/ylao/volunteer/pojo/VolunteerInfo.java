package com.iflytek.ylao.volunteer.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerInfo {
	/** 管理员编号 */
	private Integer volunteerId;
	/** 登录名 */
	private String volunteerLogin;
	/** 密码 */
	private String volunteerPwd;
	/** 用户名 */
	private String volunteerName;
	private long volunteerTel;
	private String volunteerSex;
	private short power;
}
