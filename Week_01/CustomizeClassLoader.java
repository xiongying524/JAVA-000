package com.geek.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: geek
 * @description:
 * @author: tianhailong
 * @create: 2020-10-19 19:32
 **/
public class CustomizeClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            Class<?> tmpClass = new CustomizeClassLoader().findClass("Hello");
            Object obj = tmpClass.newInstance();
            Method method = tmpClass.getMethod("hello");
            method.invoke(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        File fileName = new File("d:\\Hello.xlass");
        try{
            InputStream is = new FileInputStream(fileName);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                int b = (int)bytes[i];
                b = 255 - b;
                bytes[i] = (byte)b;
            }
//            System.out.println(new String(bytes));
            return defineClass(name,bytes,0,bytes.length);

        }catch(Exception e){
            e.printStackTrace();
        }

    return null;
    }

}
