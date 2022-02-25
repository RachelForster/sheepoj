package com.sheep.sheepoj.entity;


import lombok.extern.java.Log;

public class Role {

  private Long roleId;
  private String roleName;


  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

}
