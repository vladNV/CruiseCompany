package model.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
   private static final int RADIX = 16;

   public static String getHashCode(String text) {
       StringBuilder builder = new StringBuilder();
       try {
           MessageDigest md5 = MessageDigest.getInstance("MD5");
           md5.update(text.getBytes("UTF-8"));
           byte[] bytes = md5.digest();
           for (byte aByte : bytes) {
               builder.append(Integer.toString((aByte & 0xff) + 0x100, RADIX)
                       .substring(1));
           }

       } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       return builder.toString();
   }
}
