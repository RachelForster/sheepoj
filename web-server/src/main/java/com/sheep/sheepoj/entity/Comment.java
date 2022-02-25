package com.sheep.sheepoj.entity;


public class Comment {

  private long commentId;
  private long userId;
  private long discussId;
  private String content;
  private long prevId;


  public long getCommentId() {
    return commentId;
  }

  public void setCommentId(long commentId) {
    this.commentId = commentId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getDiscussId() {
    return discussId;
  }

  public void setDiscussId(long discussId) {
    this.discussId = discussId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getPrevId() {
    return prevId;
  }

  public void setPrevId(long prevId) {
    this.prevId = prevId;
  }

}
