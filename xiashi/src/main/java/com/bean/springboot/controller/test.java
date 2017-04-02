package com.bean.springboot.controller;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by ppctest02 on 2017/3/9.
 */
public class test {
    public static void main(String[] args) {
        System.out.println();
        try {
            /*动态加载指定类*/
            File file=new File("D:/repository/com/alibaba/fastjson/1.2.8/fastjson-1.2.8.jar");//类路径(包文件上一层)
            URL url=file.toURI().toURL();
            ClassLoader loader=new URLClassLoader(new URL[]{url});//创建类加载器
            //import com.sun.org.apache.bcel.internal.util.ClassLoader;
            //ClassLoader classLoader = new ClassLoader(new String[]{""});//类路径
            Class<?> cls=loader.loadClass("com.alibaba.fastjson.JSONArray");//加载指定类，注意一定要带上类的包名
            Object obj=cls.newInstance();//初始化一个实例
            Method method=cls.getMethod("printString",String.class,String.class);//方法名和对应的参数类型
            Object o=method.invoke(obj,"chen","leixing");//调用得到的上边的方法method
            System.out.println(String.valueOf(o));//输出"chenleixing"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
