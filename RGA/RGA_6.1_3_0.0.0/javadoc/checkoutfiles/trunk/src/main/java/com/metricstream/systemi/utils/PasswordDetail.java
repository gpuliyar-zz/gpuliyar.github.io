package com.metricstream.systemi.utils;

/**
 * PasswordDetail is an pojo class which holds encrypted & decrypted values.
 * Created by munavar.basha on 11/12/2014.
 */
public class PasswordDetail {

    /** The decode string. */
    private String dStr;
    
    /** The encode string. */
    private String eStr;

    /**
     * getdStr returns decode String value.
     *
     * @return dStr the decode String
     */
    public String getdStr() {
        return dStr;
    }

    /**
     * setdStr sets the decode value.
     *
     * @param dStr the  decode String
     */
    public void setdStr(String dStr) {
        this.dStr = dStr;
    }

    /**
     * geteStr gets encode String value.
     *
     * @return eStr the encode string
     */
    public String geteStr() {
        return eStr;
    }

    /**
     * seteStr eStr value.
     *
     * @param eStr the encode String
     */
    public void seteStr(String eStr) {
        this.eStr = eStr;
    }
}
