package com.freight.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuxin
 * @since 2021-12-20
 */
@Data
public class Lindata6 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long yr;

    private String rgDesc;

    private String cmdCode;

    private Double TradeValue;

    private String rtTitle;

    private String rtCode;

    private String ptTitle;

    private String ptCode;


}
