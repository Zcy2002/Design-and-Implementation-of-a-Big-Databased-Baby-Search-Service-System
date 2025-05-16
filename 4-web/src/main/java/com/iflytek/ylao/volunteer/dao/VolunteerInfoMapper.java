package com.iflytek.ylao.volunteer.dao;


import com.iflytek.ylao.volunteer.pojo.VolunteerInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface VolunteerInfoMapper {

	@Select("select * from volunteer_info where volunteer_login=#{volunteerLogin}")
	VolunteerInfo sltName(VolunteerInfo volunteerInfo);

	@Select("select count(*)  from volunteer_info where volunteer_login=#{volunteerLogin} and volunteer_pwd=#{volunteerPwd}")
	boolean loginIn(VolunteerInfo volunteerInfo);

	List<VolunteerInfo> listAll(Map map);

	@Select("select * from volunteer_info where volunteer_id=#{volunteerID}")
	VolunteerInfo getById(Long volunteerId);

	@Insert("insert into volunteer_info(volunteer_login,volunteer_name,volunteer_sex,volunteer_tel) values(#{volunteerLogin},#{volunteerName},#{volunteerSex},#{volunteerTel}) ")
	int insert(VolunteerInfo volunteerInfo);

	@Update("update volunteer_info set volunteer_login=#{volunteerLogin}, volunteer_name=#{volunteerName},volunteer_sex=#{volunteerSex},volunteer_tel=#{volunteerTel} where volunteer_id=#{volunteerId}")
	int update(VolunteerInfo volunteerInfo);

	@Delete("delete from volunteer_info where volunteer_id=#{volunteerId}")
	int delVolunteer(Integer volunteerId);

	@Update("update volunteer_info set volunteer_pwd=#{pwd} where volunteer_id=#{id}")
	int altPwd(Map map);
	
}
