package com.example.util;

import org.aspectj.apache.bcel.classfile.Code;
import org.hashids.Hashids;

import java.lang.annotation.Retention;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class InviteCode {

    public static String getInviteCode()
    {
        String code="";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            final int randomNum = random.nextInt(100000);
            Hashids coder = new Hashids("mysalt", 8);
            code = coder.encode(randomNum);

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return code;
    }
}
