package com.bean.test;

/**
 * Created by bean on 2016/9/12.
 */
public class C  {
    public C() {
    }

    public  void pr(int str){
        str=4321;
    }
    public static void main(String[] args) {
        C c=new C();
       int str=1234;
        c.pr(str);
        System.out.println(str);
    }
}
