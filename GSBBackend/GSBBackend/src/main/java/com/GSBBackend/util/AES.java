package com.GSBBackend.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;

@Component
public class AES {
	
	
    private static SecretKeySpec secretKey;
    
    private static byte[] key;
    
    
    @Value("${enc_dec_value}")
    private String encValue;
    
   private  static final	Logger log = LoggerFactory.getLogger(AES.class);
    
    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
//            sha = MessageDigest.getInstance("SHA-1");
//            key = sha.digest(key);
//            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public String encrypt(String strToEncrypt, String secret) 
    {
        try
        { 
//        	encValue="1";
        	if(!encValue.equalsIgnoreCase("1")) {
        		return strToEncrypt;
        	}
        	
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
            
        } 
        catch (Exception e) 
        {
            log.info("Error while encrypting: " + e.toString());
        }
        return null;
    }
    

 
    public  String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
//        	encValue="1";
        	if(!encValue.equalsIgnoreCase("1")) {
        		return strToDecrypt;
        	}
        	
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            log.info("Error while decrypting: " + e.toString());
        }
        return null;
    }
    

    public static void main(String[] args) 
    {
    	
    	
    	AES aes = new AES();
    	
        final String secretKey = "258ac4e4146a411e9dc879a9e12fd6da";
        
        String a="summeR Uniform";
        String b="summer";
        
        
        boolean res1 = a.toLowerCase().contains(b.toLowerCase());
        
        if(res1)
        {
        	log.info(" string contains a value");
        }
        else
        {
        	log.info(" string does not contains a value");
        	
        }
        

       
        String decryptedString = aes.decrypt("WUMGpNk8edQCCusAa0cXTgEYi+pY4cfwpYKc5ABfXxUZaLRZAd+JZpsfyuVX3RvsjX1c3eLYnTIAB9CLdNr3XA==",secretKey);     
          String encryptedString = aes.encrypt("{\"status\":\"np\",\"page\":1,\"size\":25,\"specialization\":\"np\"}", secretKey);
          log.info("encryptedString :"+encryptedString);
          log.info("decryptedString :"+decryptedString);
    }
    
    
    
    
    
    
    
    
    
//  public static boolean base64Checker()
//  {
//      byte[] decodedBytes = Base64.getDecoder().decode("");
//
//        Pattern BASE64_PATTERN = Pattern.compile("^[A-Za-z0-9+/]+=*={0,2}$");
//        System.out.println(BASE64_PATTERN.matcher("vasu").matches());
//		return BASE64_PATTERN.matcher("vasu").matches();
//  }

//  public static String encryptPassword(String strToEncrypt, String secret) 
//  {
//      try
//      { 
//      	 System.out.println("secret  ==   "+secret);
//      	
//
//          setKey(secret);
//          Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//          cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//          return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
//      } 
//      catch (Exception e) 
//      {
//          log.info("Error while encrypting: " + e.toString());
//      }
//      return null;
//  }
  
//  public static String decryptPassword(String strToDecrypt, String secret) 
//  {
//      try
//      {
//          setKey(secret);
//          Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
//          cipher.init(Cipher.DECRYPT_MODE, secretKey);
//          return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
//      } 
//      catch (Exception e) 
//      {
//          log.info("Error while decrypting: " + e.toString());
//      }
//      return null;
//  }
}