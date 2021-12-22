/**
  * Copyright 2021 bejson.com 
  */
package com.freight.entity;
import java.util.List;

/**
 * Auto-generated: 2021-12-20 21:8:53
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private Validation validation;
    private List<Dataset> dataset;
    public void setValidation(Validation validation) {
         this.validation = validation;
     }
     public Validation getValidation() {
         return validation;
     }

    public void setDataset(List<Dataset> dataset) {
         this.dataset = dataset;
     }
     public List<Dataset> getDataset() {
         return dataset;
     }

}