package com.metricstream.systemi.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.google.gson.Gson;
import com.msi.grcintelligence.utility.encryption.EncryptionUtilities;

/**
 * EncryptUtils class handles the encryption & decryption of passwords
 * Created by munavar.basha on 11/5/2014.
 */
@SuppressWarnings("restriction")
public class EncryptUtils {

    /** The Constant DEFAULT_ENCODING. */
    static final String DEFAULT_ENCODING="UTF-8";
    
    /** The enc. */
    static BASE64Encoder enc=new BASE64Encoder();
    
    /** The dec. */
    static BASE64Decoder dec=new BASE64Decoder();

    /**
     * Method to  encode the given String
     *
     * @param text the text
     * @return String the encoded string
     */
    public static String base64encode(String text){
        try {
            String rez = enc.encode( text.getBytes( DEFAULT_ENCODING ) );
            return rez;
        }
        catch ( UnsupportedEncodingException e ) {
            return null;
        }
    }

    /**
     * Method to decode the String
     *
     * @param text the text
     * @return String the decoded string
     */
    public static String base64decode(String text){

        try {
            return new String(dec.decodeBuffer( text ),DEFAULT_ENCODING);
        }
        catch (Exception e ) {
            return null;
        }

    }

    /**
     * readGson reads given string and converts into relevant object.
     *
     * @param <T> the generic type
     * @param jsonString the json string
     * @param classOfT the class of t
     * @return Object the Custom java object
     */
    private static <T> Object readGson(String jsonString, Class<T> classOfT)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, classOfT);
    }

    /**
     * writeGson method converts the given object into json string.
     *
     * @param object the object
     * @return String the json string 
     */
    private  static String  writeGson(Object object)
    {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    // To test the 2 above methods.
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[]  args){
        PasswordDetails pDets = new PasswordDetails();
        PasswordDetail pDetails = new PasswordDetail();
        String txt="some text to be encrypted" ;
        //String js = "{\"pDetails\":[{\"dStr\":\"some text to be encrypted\",\"eStr\":\"c29tZSB0ZXh0IHRvIGJlIGVuY3J5cHRlZA\\u003d\\u003d\"},{\"eStr\":\"mun1\"}]}";
        String js = "{\"pDetails\":[]}";
        pDetails.setdStr(txt);
        String encode = base64encode(txt);
        pDetails.seteStr(encode);
        pDets.setPasswordDetail(pDetails);
        PasswordDetail pDetail = new PasswordDetail();
        pDetail.seteStr("mun1");
       // pDetail.seteStr(base64decode("mun1"));
        pDets.setPasswordDetail(pDetail);
        //System.out.println(" eocded string  --> "+ encode);
       // System.out.println(" decode string  --> "+ base64decode(encode));
      //  System.out.println(" pDetails -- > "+ writeGson(pDets));
       // pDetails = (PasswordDetail) readGson(js, PasswordDetail.class);
       System.out.println("Encrypted values --> " + getEncryptedDetails(js) );

    }

    /**
     * getEncryptedDetails converts the given json string with encryption & decryption as needed and returns the converted string.
     *
     * @param enVal the encrypting input  value
     * @return String the encrypted string
     */
    public static String getEncryptedDetails(String enVal){
    	System.out.println("****************** "+enVal);
        PasswordDetails pDetails = (PasswordDetails) readGson(enVal, PasswordDetails.class);
        List<PasswordDetail> pDetailArray = pDetails.getPasswordDetail();
        PasswordDetails tempPasswordDetails = new PasswordDetails();
        for(int ival=0; ival < pDetailArray.size(); ival++){
            PasswordDetail pDetail = (PasswordDetail) pDetailArray.get(ival);
            if(pDetail.getdStr()!= null && pDetail.getdStr().length() > 0){
                pDetail.seteStr(EncryptionUtilities.encrypt(pDetail.getdStr()));
            }else if(pDetail.geteStr()!= null && pDetail.geteStr().length() > 0){
                pDetail.setdStr(EncryptionUtilities.decrypt(pDetail.geteStr()));
            }
            tempPasswordDetails.setPasswordDetail(pDetail);
        }

        return writeGson(tempPasswordDetails);
    }


}
