package com.oracle.cegbu.aconex.commons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

abstract class Person{  
	  abstract void eat();  
	}  
	class Palin{  
	 public static void main(String args[]){  
	  Person p=new Person(){  
	  void eat(){System.out.println("nice fruits");}  
	  };  
	  p.eat(); 
	 }  
	}  
