package com.sheep.sheepoj.entity;


public class Contest {

  private long contestId;
  private long participation;
  private java.sql.Date start;
  private long length;
  private String name;
  private long status;


  public long getContestId() {
    return contestId;
  }

  public void setContestId(long contestId) {
    this.contestId = contestId;
  }


  public long getParticipation() {
    return participation;
  }

  public void setParticipation(long participation) {
    this.participation = participation;
  }


  public java.sql.Date getStart() {
    return start;
  }

  public void setStart(java.sql.Date start) {
    this.start = start;
  }


  public long getLength() {
    return length;
  }

  public void setLength(long length) {
    this.length = length;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
