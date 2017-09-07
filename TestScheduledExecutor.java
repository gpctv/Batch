package com.stan.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduledExecutor implements Runnable{
    private int i;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService e=Executors.newSingleThreadScheduledExecutor();
		TestScheduledExecutor t=new TestScheduledExecutor();
		
		e.scheduleAtFixedRate( t, 0, 7, TimeUnit.SECONDS);
	  
	}
	
	public static void printTask(int a){
		System.out.println(a);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		i=i+1;
		printTask(i);
	}

}
