package com.sheep.sheepoj.entity;


public class Discuss {

  private long discussId;
  private String title;
  private String content;
  private long likeNum;
  private long discussNum;
  private String publisherId;
  private java.sql.Timestamp date;
  private boolean isLiked;

  public void setIsLiked(boolean liked) {
    isLiked = liked;
  }

  public boolean getIsLiked() {
    return isLiked;
  }

  public long getDiscussId() {
    return discussId;
  }

  public void setDiscussId(long discussId) {
    this.discussId = discussId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getLikeNum() {
    return likeNum;
  }

  public void setLikeNum(long likeNum) {
    this.likeNum = likeNum;
  }


  public long getDiscussNum() {
    return discussNum;
  }

  public void setDiscussNum(long discussNum) {
    this.discussNum = discussNum;
  }


  public String getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(String publisherId) {
    this.publisherId = publisherId;
  }


  public java.sql.Timestamp getDate() {
    return date;
  }

  public void setDate(java.sql.Timestamp date) {
    this.date = date;
  }

}
