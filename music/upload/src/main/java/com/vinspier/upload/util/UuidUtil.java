package com.vinspier.upload.util;

import java.util.UUID;

public class UuidUtil {

    private UuidUtil() {

    }

    public static String genUuid(){
        return genUuid(32);
    }

    public static String genUuid(int len){
        return UUID.randomUUID().toString().replaceAll("-","").substring(0,len);
    }

    public static String genDefaultUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
