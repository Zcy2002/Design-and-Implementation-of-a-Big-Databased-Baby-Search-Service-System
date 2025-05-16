package com.iflytek.ylao.missinfo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissInfo {

    private String category;
    private Integer id;
    private String name;
    private String sex;
    private String birthday;
    private String missDecadeStr;
    private String missAgeStr;
    private String height;
    private String misstime;
    private String locationprovince;
    private String placeprovince;
    private String feature;
    private String notes;
    private String volunteer;

}
