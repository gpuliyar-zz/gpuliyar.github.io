package com.metricstream.rga.sc.testsc;

public enum EnumInfolet
{
  INSTANCE;
  
  private EnumInfolet() {}
  
  public Object invoke()
  {
    return "enum success";
  }
}
