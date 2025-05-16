package com.iflytek.ylao.root.dao;

import com.iflytek.ylao.root.pojo.RootInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface RootInfoMapper {



	@Select("select * from root_info where name=#{name}")
	RootInfo sltName(RootInfo rootInfo);


	@Select("select count(*)  from root_info where name=#{name} and pwd=#{pwd}")
	boolean loginIn(RootInfo rootInfo);



	@Update("update root_info set pwd=#{pwd} where id=1")
	int altPwd(String pwd);


}
