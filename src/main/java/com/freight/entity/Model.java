package com.freight.entity;

import lombok.Data;

/**
 * Auto-generated: 2021-09-23 9:42:59
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Model {
    // 6位市级标识码
    private int year;

    private String group;

    private String name;

    private String TradeValue;

    public Model(int i, String aa, String a, String i1) {
        year=i;
        group=aa;
        name=a;
        TradeValue=i1;
    }
}