/**
  * Copyright 2021 bejson.com 
  */
package com.freight.entity;

/**
 * Auto-generated: 2021-12-20 21:8:53
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Validation {

    private Status status;
    private String message;
    private Count count;
    private DatasetTimer datasetTimer;
    public void setStatus(Status status) {
         this.status = status;
     }
     public Status getStatus() {
         return status;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setCount(Count count) {
         this.count = count;
     }
     public Count getCount() {
         return count;
     }

    public void setDatasetTimer(DatasetTimer datasetTimer) {
         this.datasetTimer = datasetTimer;
     }
     public DatasetTimer getDatasetTimer() {
         return datasetTimer;
     }

}