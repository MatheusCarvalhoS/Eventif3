package br.edu.ifg.tads.mtp.eventif.util;
import java.security.*;
import java.math.*;

public class MD5 {
    public String gerarSenha(String senha) throws Exception{
       MessageDigest m=MessageDigest.getInstance("MD5");
       m.update(senha.getBytes(),0,senha.length());
       return new BigInteger(1,m.digest()).toString(16);
    }
}