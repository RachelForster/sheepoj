package com.sheep.sheepoj.entity;



public class Status {

  private long userId;
  private long problemId;
  private long verdict;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getProblemId() {
    return problemId;
  }

  public void setProblemId(long problemId) {
    this.problemId = problemId;
  }


  public long getVerdict() {
    return verdict;
  }

  public void setVerdict(long verdict) {
    this.verdict = verdict;
  }

}
