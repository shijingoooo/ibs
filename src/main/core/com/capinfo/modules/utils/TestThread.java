package com.capinfo.modules.utils;

import java.util.Date;

public class TestThread extends Thread {

	int pauseTime;
    String name;
    
    public TestThread(int x, String n) {
        pauseTime = x;
        name = n;
    }
    public void run() {
        while(true) {
            try {
                System.out.println(name + ":" + new 
                    Date(System.currentTimeMillis()));
                Thread.sleep(pauseTime);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
    static public void main(String args[]) {
    	TestThread tt1 = new TestThread(1000, "Zhan");
        tt1.start();
        TestThread tt2 = new TestThread(3000, "Wang");
        tt2.start();
    
    }
}
