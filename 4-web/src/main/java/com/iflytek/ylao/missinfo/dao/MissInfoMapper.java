package com.iflytek.ylao.missinfo.dao;

import com.iflytek.ylao.missinfo.pojo.MissInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MissInfoMapper {
    List<MissInfo> listAll(Map map);

    @Select("select category,id,name,sex,birthday,height,misstime,missDecadeStr,missAgeStr,locationprovince,placeprovince,feature,notes,volunteer from find_info where id=#{id}")
    MissInfo getById(Long id);

    @Insert("insert into find_info(category,id,name,sex,birthday,height,misstime,missDecadeStr,missAgeStr,locationprovince,placeprovince,feature,notes,volunteer) " +
            "values(#{category},#{id},#{name},#{sex},#{birthday},#{height},#{misstime},#{missDecadeStr},#{missAgeStr},#{locationprovince},#{placeprovince},#{feature},#{notes},#{volunteer})")
    int insert(MissInfo missinfo);

    @Update("update find_info set category=#{category}, id=#{id},name=#{name}, sex=#{sex},birthday=#{birthday},height=#{height},misstime=#{misstime},missDecadeStr=#{missDecadeStr},missAgeStr=#{missAgeStr},locationprovince=#{locationprovince},placeprovince=#{placeprovince},feature=#{feature},notes=#{notes},volunteer=#{volunteer} where id=#{id}")
    int update(MissInfo missinfo);

    @Delete("delete from find_info where id=#{id}")
    int delete(Integer id);
}