package com.sheep.sheepoj.entity;


public class Data {

  private long id;
  private long problemId;
  private String dataInput;
  private String dataOutput;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getProblemId() {
    return problemId;
  }

  public void setProblemId(long problemId) {
    this.problemId = problemId;
  }


  public String getDataInput() {
    return dataInput;
  }

  public void setDataInput(String dataInput) {
    this.dataInput = dataInput;
  }


  public String getDataOutput() {
    return dataOutput;
  }

  public void setDataOutput(String dataOutput) {
    this.dataOutput = dataOutput;
  }

}
