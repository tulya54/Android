  package com.example.myapplication;
  
  private long versionCode;
    private String result;
    public ModelVersionCode() {}
    public ModelVersionCode(long versionCode, String result) {
        this.versionCode = versionCode;
        this.result = result;
    }
    public long getVersionCode() { return versionCode; }
    public void setVersionCode(long versionCode) { this.versionCode = versionCode; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
