package com.iflytek.ylao.root.service;


import com.iflytek.ylao.root.dao.RootInfoMapper;
import com.iflytek.ylao.root.pojo.RootInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootInfoService {

    @Autowired
    private RootInfoMapper rootInfoMapper;


    public RootInfo sltName(RootInfo rootInfo) {
    	return rootInfoMapper.sltName(rootInfo);
    }

    public boolean loginIn(RootInfo rootInfo){
        return rootInfoMapper.loginIn(rootInfo);
    }

    public int altPwd(String pwd) {
    	return rootInfoMapper.altPwd(pwd);
    }

}
