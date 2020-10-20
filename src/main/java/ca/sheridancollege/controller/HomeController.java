package ca.sheridancollege.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	
	@GetMapping ("/") 
	public String goHome () {
		return "user.html";
	}

	
	@GetMapping ("/result")
	public String goResult (@RequestParam int c, @RequestParam int r, @RequestParam double p, Model model) {
		
		String block = "";
	    LinkedList <Integer> vVal = new LinkedList <Integer> (); 
	    Stack <Integer> jVal = new Stack <Integer> (); 
	    ArrayList <Integer> iVal = new ArrayList<Integer>(); 
	    ArrayList <Integer> checkList = new ArrayList (); 
	   
	    
		int[][] matrix = new int[r][c];
	     for (int row = 0; row < matrix.length ; row++) { 
	    	    for (int col = 0; col < matrix[row].length; col++) {
	    	    	double random = Math.random();
	    	    	if (random > p) {
	    	    		int newRandom = (int)(Math.random()*10);
	    	        matrix[row][col] = newRandom;
	    	        checkList.add(newRandom);
	    	        
	    	        
	    	        if (newRandom != 0) {
	    	        vVal.add(newRandom);
	    	        jVal.add(col);
	    	        } 
	    	        	
	    	        
	    	    	}
	    	    }
	    	}
	     
	     int insideChecker =0;
			for (int row = 0; row < matrix.length; row++) {
				for (int col = 0; col < matrix[row].length; col++) {
					if (matrix[row][col] != 0) {
						iVal.add(vVal.indexOf(matrix[row][col]));
						
						break;
					}
				}
			}
	     
	     
	     
	     for (int[] x : matrix)
	     {
	        for (int y : x)
	        {
	             block += y + " "; 
	             
	        }
	        block += "\n";
	     }
		
	     model.addAttribute("matrix", block);
	     model.addAttribute("vVal", vVal);
	     model.addAttribute("jVal", jVal);
	     model.addAttribute("iVal", iVal);
		 return "result.html";
	}
	
	
	
	
	
	@GetMapping("/fileRead")
	public String goFileRead (Model model) throws FileNotFoundException {
		ArrayList <Integer> entireList = new ArrayList ();
		ArrayList <Integer> vVal = new ArrayList ();
		ArrayList <Integer> jVal = new ArrayList ();
		ArrayList <Integer> iVal = new ArrayList ();
		String s = "";
		int checker =0;
		String block="";
		int [][] matrix = new int[4][5];
		File inFile = new File ("C:/Users/Tahr/Documents/workspace-spring-tool-suite-4-4.7.2.RELEASE/W5_A1/src/main/resources/static/1.txt");
		if (inFile.exists()) {
			Scanner sc = new Scanner (inFile);
			while (sc.hasNextLine()) {
				String record = sc.nextLine(); 
				String [] fields = record.split(" ");
				
				if (checker > 0) {
					for (int i=0; i<fields.length;i++) {
					entireList.add(Integer.parseInt(fields[i]));
					}
				}
				checker++;
			}
			 
		}
		for (int i=0; i<entireList.size(); i++) {
			if(i == 5) block += "\n";
			if(i == 10) block += "\n";
			if(i == 15) block += "\n";
            block += entireList.get(i) + " "; 
            
            if (entireList.get(i) > 0) {
            vVal.add(entireList.get(i));
            jVal.add(i);
            }
       }
		
			for (int row = 0; row < matrix.length; row++) {
				for (int col = 0; col < matrix[row].length; col++) {
					if (matrix[row][col] != 0) {
						iVal.add(vVal.indexOf(matrix[row][col]));
						
						break;
					}
				}
			}
			
		
		model.addAttribute("matrix", block);
		model.addAttribute("vVal", vVal);
		model.addAttribute("jVal", jVal);
		model.addAttribute("iVal", iVal);
		return "fileRead.html ";
	}
	

	
}
