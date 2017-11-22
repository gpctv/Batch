package com.stan.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

public class TestMoveFile {
	private String path;//程式執行路徑
	
	/**
	 * 初始化
	 */
	public TestMoveFile(){
		 path=System.getProperty("user.dir");
		 init();
		 System.out.println("程式目前路徑"+path);
	}
	/**
	 * 初始化LOG檔
	 */
	private void init(){
		try{
		File file=new File(path+"\\log.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
	     TestMoveFile moveFile=new TestMoveFile();
	     moveFile.exec(args[0], args[1]);
	}
	
	/**
	 * 獲取Folder LIST
	 * @param path
	 * @return
	 */
    private File[] getFileList(String path){
    	File a=new File(path);
    	return a.listFiles();
    }
    /**
     * 移動檔案
     * @param a
     * @param dec
     * @return
     */
    private String moveFile(File a,String dec){
    	 if(a.renameTo(new File(dec))){
    		 return "SUCCESS";
    	 }else{
    		 return "ERROR";
    	 }
    	
    }
    /**
     * 抓取父目錄所有檔案
     * 讀取最新數字檔名
     * 寫下最後一筆數字檔名
     * @param source
     * @param dec
     */
    private void exec(String source,String dec){ 
    	File files[]=getFileList(source);
    	BigDecimal fileName=readMaxNum();
    	BigDecimal num=new BigDecimal("1");
    	for(File file:files){
		//如果是檔案就直接搬
    		if(file.isFile()){
			System.out.println(file.getAbsolutePath());
    			System.out.println("Move "+moveFile(file,dec+"\\"+fileName.toString()+".jpg"));
   			 fileName= fileName.add(num)  ;
    		}else{
    			for(File s: file.listFiles()){
				if(s.isFile()){//子目錄底下如果是檔案才能搬
    			System.out.println(s.getAbsolutePath());
    			 System.out.println("Move "+moveFile(s,dec+"\\"+fileName.toString()+".jpg"));
    			 fileName= fileName.add(num)  ;
				}
    		}
    		}
    	}
    	writeNum(fileName);
    }
    
    
    /**
     * 讀取目前數字檔名
     * log.txt為空時給0
     * @return
     */
    private BigDecimal readMaxNum(){ 
			 
			String b=read();
            if(b.toString().trim().equals("")){
            	 return	BigDecimal.valueOf(0);
            }else{
            	BigDecimal num=new BigDecimal(b.toString().trim());
                return num;
            } 
    }
    /**
     * 讀取檔案
     * @return
     */
    private String read(){
    	String line = null;
    	  StringBuilder b=new StringBuilder();
        // Always wrap FileReader in BufferedReader.
    	try{
        @SuppressWarnings("resource")
		BufferedReader bufferedReader = 
            new BufferedReader(new InputStreamReader(
                    new FileInputStream(path+"\\log.txt"),"BIG5")); 
        while((line = bufferedReader.readLine()) != null) {
            b.append(line);
            b.append("\r\n");
        }
        System.out.println("log:"+b.toString()); 
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		       e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return b.toString();
    }
    
    
    /**
     * 寫入數字檔名 log.txt
     * @param i
     */
   private void writeNum(BigDecimal i){
	   write(i.toString());
   } 
    
    /**
     * 寫入log.txt
     * @param i
     */
    private void write(String i){ 
    	 BufferedWriter bufferedWriter =null;  
         try { 
        	   bufferedWriter =
                     new BufferedWriter(new OutputStreamWriter(
                             new FileOutputStream(path+"\\log.txt"),"BIG5" ));
			bufferedWriter.write(i);
			close(bufferedWriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(bufferedWriter);
		}
         
          
    }
    /**
     * 關閉bufferWriter
     * @param w
     */
    private void close(BufferedWriter w){
    	try{
    		if(w !=null){
				w.close();
			}
    	}catch(IOException e){
			e.printStackTrace();
		}
    }
}
