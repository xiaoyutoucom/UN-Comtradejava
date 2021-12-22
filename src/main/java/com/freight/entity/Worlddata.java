package com.freight.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Worlddata对象", description="")
public class Worlddata implements Serializable {

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
