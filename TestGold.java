package com.stan.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestGold {
	private static String url = "http://rate.bot.com.tw/gold/chart/day/TWD";
	private static Document doc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
			getDate(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 public static void getDate(String url) throws IOException{
		 HashMap<String,Double> map=new HashMap<String,Double>();
		 doc=Jsoup.connect(url).get();
		 System.out.println( doc.title());
		 Elements table=doc.getElementsByTag("table");
		 Elements tbody=table.select("tbody");
		 Elements tr=tbody.select("tr");
		 for(Element e:tr.next()){
			   System.out.print(e.select("td").get(0).text()+"--");
			   System.out.print(e.select("td").get(1).text()+"||");
			   System.out.print(e.select("td").get(2).text()+"||");
			   System.out.print(comma(new BigDecimal(e.select("td").get(3).text())) +"||");
			   System.out.println(comma(new BigDecimal(e.select("td").get(4).text())) );
				    
			} 
	 }
	
	//轉換JSON格式
	public static void pareJSON(HashMap<String,Double> o){
		JSONObject j=new JSONObject(o);
		 
	}
	
	public static boolean isNum(String num){
		 Pattern pattern=Pattern.compile("^([-+]?\\d+)(\\.\\d+)?$");
		 return pattern.matcher(num).matches();
		 
	}
	public static String comma(BigDecimal num){
		DecimalFormat format=new DecimalFormat("#,###");
		return format.format(num);
	}
}
