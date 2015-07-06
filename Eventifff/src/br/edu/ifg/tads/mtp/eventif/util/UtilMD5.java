package br.edu.ifg.tads.mtp.eventif.util;

import java.security.*;
import java.math.*;

public class UtilMD5 {
    public static void main(String args[]) throws Exception{
       String s="123";
       MessageDigest m=MessageDigest.getInstance("MD5");
       m.update(s.getBytes(),0,s.length());
       System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
    }
}
