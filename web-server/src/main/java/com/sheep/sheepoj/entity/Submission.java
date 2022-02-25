package com.sheep.sheepoj.entity;

public class Submission {

  private long submissionId;
  private long problemId;
  private long userId;
  private long contestId;
  private String language;
  private String code;
  private String verdict;
  private double time;
  private double memory;


  public long getSubmissionId() {
    return submissionId;
  }

  public void setSubmissionId(long submissionId) {
    this.submissionId = submissionId;
  }


  public long getProblemId() {
    return problemId;
  }

  public void setProblemId(long problemId) {
    this.problemId = problemId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getContestId() {
    return contestId;
  }

  public void setContestId(long contestId) {
    this.contestId = contestId;
  }


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getVerdict() {
    return verdict;
  }

  public void setVerdict(String verdict) {
    this.verdict = verdict;
  }


  public double getTime() {
    return time;
  }

  public void setTime(double time) {
    this.time = time;
  }


  public double getMemory() {
    return memory;
  }

  public void setMemory(double memory) {
    this.memory = memory;
  }

}
