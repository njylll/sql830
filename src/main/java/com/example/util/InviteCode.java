package com.example.util;

import org.aspectj.apache.bcel.classfile.Code;
import org.hashids.Hashids;

import java.lang.annotation.Retention;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class InviteCode {

    public static String getInviteCode()
    {
        String code="";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            final int randomNum = random.nextInt(1000000);
            Hashids coder = new Hashids("mysalt", 8);
            code = coder.encode(randomNum);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return code;
    }
}
