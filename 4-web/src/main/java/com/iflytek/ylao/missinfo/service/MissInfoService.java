package com.iflytek.ylao.missinfo.service;


import com.iflytek.ylao.missinfo.dao.MissInfoMapper;
import com.iflytek.ylao.missinfo.pojo.MissInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MissInfoService {

    @Autowired
    private MissInfoMapper missinfoMapper;

    public List<MissInfo> listAll(Map map) {
        return missinfoMapper.listAll(map);
    }

    public MissInfo getById(Long id) {
        return missinfoMapper.getById(id);
    }

    public int insert(MissInfo missinfo) {
        return missinfoMapper.insert(missinfo);
    }

    public int update(MissInfo missinfo) {
        return missinfoMapper.update(missinfo);
    }

    public int delete(Integer id) {
        return missinfoMapper.delete(id);
    }
}
