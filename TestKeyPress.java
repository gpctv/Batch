package com.stan.test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener; 

public class TestKeyPress implements NativeKeyListener ,NativeMouseInputListener{

	private String path;
	public TestKeyPress(){
		 path=System.getProperty("user.dir");
	}
	
	public static void main(String arg[]){
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		TestKeyPress test=new TestKeyPress();
		GlobalScreen.addNativeKeyListener(test);
		GlobalScreen.addNativeMouseListener(test);
		GlobalScreen.addNativeMouseMotionListener(test);
	}
	
	
	public void nativeKeyPressed(NativeKeyEvent e)  {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		 write("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        write("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		 write("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}


	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse clicked:" + e.getX());
	}


	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse Press:" + e.getButton());
	}


	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse Release:" + e.getButton());
	}


	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("mouse move" + e.getX());
 
	}


	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		 System.out.println("mouse Dragge:" + e.getX()+","+e.getY()); 
	}

	private void write(String i){ 
   	 BufferedWriter bufferedWriter =null;
   	 Date date=Calendar.getInstance().getTime();
        try { 
       	   bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(path+"\\log2.txt",true),"BIG5" ));
			bufferedWriter.write(date.toString()+":"+i+"\r\n");
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
