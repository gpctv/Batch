package com.stan.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestForm extends TimerTask{
	private static String  message;
	
	public static void main(String args[]){
		int hh,mm,ss;
		String inputMessage;
		String time=JOptionPane.showInputDialog("set HH:mm:ss");
		inputMessage=JOptionPane.showInputDialog("set message");
		String d[]=time.split(":");
		hh=Integer.parseInt(d[0]);
		mm=Integer.parseInt(d[1]);
		ss=Integer.parseInt(d[2]);  
		message=inputMessage;
		log("setTime:"+hh+":"+mm+":"+ss+" message:"+message);
		log("path:"+System.getProperty("user.dir"));
		System.out.println("setTime:"+hh+":"+mm+":"+ss+" message:"+message);
		Timer timer=new Timer();
		TestForm task=new TestForm();
		timer.schedule(task,setTime(hh,mm, ss));
		
		
	}
 
	private static void pop(){
		JFrame frame = new JFrame("JOptionPane");
	    frame.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(frame, message);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(message);
	 	pop();
	 	System.exit(0);
	}
	
	private static Date setTime(int hh,int mm,int ss){
		Calendar date=Calendar.getInstance();
		 date.set(Calendar.HOUR,hh);
		 date.set(Calendar.MINUTE,mm);
		 date.set(Calendar.SECOND,ss);
		return date.getTime();
	}
	
	private static void log(String loge){
		String currentPath=System.getProperty("user.dir");
		 Logger log=Logger.getLogger("TestForm");
		 try {
			FileHandler file=new FileHandler(currentPath+"/log.txt");
			SimpleFormatter sformatter = new SimpleFormatter();
			file.setEncoding("UTF-8");
			file.setFormatter(sformatter);
			log.addHandler(file);
			log.info(loge);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	}
}
