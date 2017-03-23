package com.stan.test;

import java.util.Scanner;

public class TestNumToChn {
	static String [] chineseNumber = {"零","壹","貳","參","肆","伍","陸","柒","捌","玖"};
    static String [] chineseUnit = {"","拾","佰","仟","萬","拾萬","佰萬","仟萬","億","拾億","佰億","仟億","兆","拾兆"};
    static StringBuffer str;
    static Long money;
    static String input;
    static int index;
    static boolean naZero;
    static Scanner sc = new Scanner(System.in);
	 public static void main(String args[]){
         while( sc.hasNext() ){
                    str = new StringBuffer();
                    money = sc.nextLong();
                    input = money.toString();
                    index = input.length();
                    naZero = false;

                    for( char a : input.toCharArray() ){
                         index--;

                         if( a > '0' ){
                               if( naZero ){
                                     str.append(chineseNumber[0]);
                                     naZero = false;
                               }
                               str.append(chineseNumber[(int)(a-'0')]+chineseUnit[index]);
                         }
                         else{
                                  naZero = true;
                         }
    
                         String b = chineseUnit[index <= 7 ? 4 : 8];
                         int c = str.indexOf(b);
                         int d = str.lastIndexOf(b);
                         if( c == d ){
                                  continue;
                         }
                         else{
                                  str.deleteCharAt(c);
                         }
                    }
   
                    if( str.length() == 0 ){
                           str.append(chineseNumber[0]);
                    }
   
                    System.out.println(str);
         };
     }
}
