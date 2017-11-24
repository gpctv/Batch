package com.stan.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestThread implements Runnable{

	private int threadName;
	private int total=100;
	private String sourcePath;
	private String descPath;
	private static String sourFileName="test.exe";
	private String desFileName="test.exe";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1=new Thread(new TestThread(1,"D:/fileFolder/"+sourFileName,"D:/Pos1"));
		Thread t2=new Thread(new TestThread(2,"D:/fileFolder/"+sourFileName,"D:/Pos2"));
		t1.start();
		t2.start();
	}
	  public TestThread(int i,String sour,String desc) {
		// TODO Auto-generated constructor stub
		  this.threadName=i;
		  this.sourcePath=sour;
		  this.descPath=desc;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//synchronized (TestThread.class){
		/**for(int i=total;total>0;total--){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("threadName:"+threadName+" "+total);
		}**/
			
			File source =new File(sourcePath);
			File target=new File(descPath);
			InputStream in=null;
			OutputStream out=null;
			
			 
			 if(!target.exists()){
				boolean result=target.mkdir();
				System.out.println("threadName:"+threadName +"create:"+result );
			} 
			try {
				  in=new FileInputStream(source);
				  out=new FileOutputStream(target.getAbsolutePath()+"/"+desFileName);
				byte[] bf=new byte[1024];
				BigDecimal total=BigDecimal.valueOf(source.length());
				int len;
				BigDecimal size=BigDecimal.valueOf(0);
				BigDecimal progress=new BigDecimal(0);
				while((len = in.read(bf)) > 0){
					size=size.add(BigDecimal.valueOf(len));
					progress=size.divide(total,5,RoundingMode.CEILING);
					System.out.println("progress"+threadName+":"+progress.toString());
					out.write(bf, 0, len);
				}
				close(in,out);
				System.out.println("threadName "+threadName+"finish");
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(in,out);
			}
			
		}
	//}
	
	private void close(InputStream in,OutputStream out){
		try{
		if(in!=null){
			in.close();
		
		}
		if(out!=null){
			out.close();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
