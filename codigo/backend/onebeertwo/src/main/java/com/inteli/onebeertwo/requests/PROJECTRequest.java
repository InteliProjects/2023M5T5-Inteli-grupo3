package com.inteli.onebeertwo.requests;

public class PROJECTRequest {
  private String name;

  public PROJECTRequest() {
  }

  public PROJECTRequest(String name) {
    this.name = name;
  }

  public String getName() {
      return name;
  }
  
  public void setName(String name) {
      this.name = name;
  }
}
