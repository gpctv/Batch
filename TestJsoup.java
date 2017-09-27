package com.stan.test;

import java.util.HashMap; 
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; 
import org.jsoup.select.Elements;

public class TestJsoup {
	private static String url = "http://rate.bot.com.tw/xrt?Lang=zh-TW";
	private static Document doc;
	public static void main(String[] argv){
		 getDate(url);
	 }
	//抓取幣值資料
	 public static void getDate(String url){
		 try{
			 HashMap<String,Double> map=new HashMap<String,Double>();
			 doc=Jsoup.connect(url).get();
			 System.out.println( doc.title());
			 Elements table=doc.getElementsByTag("table");
			 Elements tbody=table.select("tbody");
			 Elements div=tbody.select("div[class=hidden-phone print_show]");
			 Elements td= table.select("td[data-table=本行現金賣出]");
			 Elements dollar=td.select("td[class=rate-content-cash text-right print_hide]");
			for(int i=0;i<div.size();i++){
				double d=0;
				if(isNum(dollar.get(i).text())){
					d=Double.valueOf(dollar.get(i).text());
				}else{
					d=0;
				}
				map.put(div.get(i).text(), d);
			
				System.out.print(div.get(i).text()+":");
				 System.out.println(dollar.get(i).text());
			}
			 pareJSON(map);
		 }catch(Exception e){
			 
		 }
	 }
	 //判斷回傳是否為數字
public static boolean isNum(String num){
	 Pattern pattern=Pattern.compile("^([-+]?\\d+)(\\.\\d+)?$");
	 return pattern.matcher(num).matches();
	 
}
//轉換JSON格式
public static void pareJSON(HashMap<String,Double> o){
	JSONObject j=new JSONObject(o);
	System.out.println(j.get("歐元 (EUR)"));
	System.out.println(o.get("歐元 (EUR)"));
}
}
