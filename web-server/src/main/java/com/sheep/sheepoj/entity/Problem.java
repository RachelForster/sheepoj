package com.sheep.sheepoj.entity;


public class Problem {

  private long problemId;
  private String name;
  private String level;
  private String description;
  private String timeLimit;
  private String memoryLimit;
  private long passNum;
  private long submissionNum;


  public long getProblemId() {
    return problemId;
  }

  public void setProblemId(long problemId) {
    this.problemId = problemId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getTimeLimit() {
    return timeLimit;
  }

  public void setTimeLimit(String timeLimit) {
    this.timeLimit = timeLimit;
  }


  public String getMemoryLimit() {
    return memoryLimit;
  }

  public void setMemoryLimit(String memoryLimit) {
    this.memoryLimit = memoryLimit;
  }


  public long getPassNum() {
    return passNum;
  }

  public void setPassNum(long passNum) {
    this.passNum = passNum;
  }


  public long getSubmissionNum() {
    return submissionNum;
  }

  public void setSubmissionNum(long submissionNum) {
    this.submissionNum = submissionNum;
  }

}
