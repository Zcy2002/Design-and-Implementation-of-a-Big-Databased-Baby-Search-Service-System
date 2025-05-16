package com.iflytek.ylao.volunteer.service;

import com.iflytek.ylao.volunteer.dao.VolunteerInfoMapper;
import com.iflytek.ylao.volunteer.pojo.VolunteerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class VolunteerInfoService {

    @Autowired
    private VolunteerInfoMapper volunteerInfoMapper;

    public VolunteerInfo sltName(VolunteerInfo volunteerInfo){
        return volunteerInfoMapper.sltName(volunteerInfo);
    }

    public boolean loginIn(VolunteerInfo volunteerInfo){
        return volunteerInfoMapper.loginIn(volunteerInfo);
    }

    public List<VolunteerInfo> listAll(Map map) {
    	return volunteerInfoMapper.listAll(map);
    }

    public VolunteerInfo getById(Long volunteerId) {
    	return volunteerInfoMapper.getById(volunteerId);
    }

    public int insert(VolunteerInfo volunteerInfo) {
    	return volunteerInfoMapper.insert(volunteerInfo);
    }

    public int update(VolunteerInfo volunteerInfo) {
    	return volunteerInfoMapper.update(volunteerInfo);
    }

    public int delVolunteer(Integer volunteerId) {
    	return volunteerInfoMapper.delVolunteer(volunteerId);
    }

    public int altPwd(Map map) {
        return volunteerInfoMapper.altPwd(map);
    }

}
