package com.lcj.util;


public class ByteBufferUtil {

	public static void main(String[] args) {
		
		
		System.out.println(charToByteAscii('q'));  
        System.out.println(byteAsciiToChar(113)); 
        
        
        
         

	}

	/** 
     * 方法一：将char 强制转换为byte 
     * @param ch 
     * @return 
     */  
    public static byte charToByteAscii(char ch){  
        byte byteAscii = (byte)ch;  
          
        return byteAscii;  
    }  
    
    /** 
     * 同理，ascii转换为char 直接int强制转换为char 
     * @param ascii 
     * @return 
     */  
    public static char byteAsciiToChar(int ascii){  
        char ch = (char)ascii;  
        return ch;  
    }  
	

}
