package com.iflytek.ylao;

import com.iflytek.ylao.volunteer.dao.VolunteerInfoMapper;
import com.iflytek.ylao.volunteer.pojo.VolunteerInfo;
import com.iflytek.ylao.volunteer.service.VolunteerInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YlaoApplicationTests {
    @Autowired
    VolunteerInfoMapper dao;

    @Autowired
    VolunteerInfoService service;

    @Test
    void contextLoads() {
//        AdminInfo ai = dao.getById(1l);
//        System.out.println(ai.getAdminName());
        VolunteerInfo ai = service.getById(1L);
        System.out.println(ai.getAdminName());
    }

}
