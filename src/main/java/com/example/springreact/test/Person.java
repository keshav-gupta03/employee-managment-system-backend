package com.example.springreact.test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Person {
    private String[] hobbies;
    
   
    String[] getHobbies(){
    	byte[] arr = new byte[4];
    	InetAddress server = null;
    	
    	Arrays.copyOf(arr, arr.length);
    	ArrayList<String> al = new ArrayList<>();
    	al.add("sdfdsf");
    	List<String> all =  List.copyOf(al);
    	System.out.println(all.get(0));
    	return Arrays.copyOf(hobbies,hobbies.length);
    
    	
    }
    
    void setHobbies(String[] hobbies){
    	X509Certificate[] arr = new X509Certificate[34];
    	Arrays.copyOf(arr,arr.length);
    	
    	this.hobbies = hobbies;
    }
    public static void main(String[] argfs) {
    	Person p = new Person();
    	p.getHobbies();
    }
    
}