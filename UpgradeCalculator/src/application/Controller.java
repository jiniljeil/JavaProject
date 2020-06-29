package application;

import java.util.ArrayList;
import java.math.*; // 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
   
   private String operator = "";
   private double dx = 0;
   private String expression;
   
   @FXML
   public Text result;
   @FXML
   public Text expr;
   @FXML 
   private void expression(ActionEvent event) {
	   
      result.setText(result.getText() + ((Button) event.getSource()).getText());
      expression = result.getText();
      expr.setText(expression);
   }
   
   
   // Clear
   @FXML 
   private void func(ActionEvent event) {
      if(((Button) event.getSource()).getText().equals("C")){
         result.setText("");
         expr.setText("");
      }else if(((Button) event.getSource()).getText().equals("D")){
         String tmp = new String(result.getText());
         String remove = tmp.substring(0, tmp.length()-1);
         expr.setText(remove);
         result.setText(remove);
      }else if(((Button) event.getSource()).getText().equals("=")){
         // result
         int count = 0;
         
         for(int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) == 'X' || expression.charAt(i) == '¡À' || expression.charAt(i) == '%' 
                  || expression.charAt(i) == '-' || expression.charAt(i) == '+') {
               count++;
            }
         }

         for(int i = 0 ;i < count; i++) {
            
            if(expression.contains("X") || expression.contains("¡À") || expression.contains("%")) {
               
               String operator = "";
               for(int j = 0; j < expression.length(); j++) {
                  if(expression.charAt(j) == 'X' || expression.charAt(j) == '¡À' || expression.charAt(j) == '%') {
                     operator = expression.charAt(j)+"";
                     break;
                  }
               }
               
               int idx = expression.indexOf(operator);
               
               int startIdx = 0 ;
               int endIdx = 0;
               for(int j = idx -1 ;  j >= 0; j-- ) {
                  if(isOperator(expression.charAt(j))) {
                     startIdx = j+1;
                     break;
                  }
               }
               for(int j = idx + 1; j < expression.length(); j++) {
                  if(isOperator(expression.charAt(j))) {
                     endIdx = j-1 ;
                     break;
                  }
               }
               String first = "";
               String second = "";
            
               
               if( endIdx == 0) { //  3 + 5 * 4
                     double x  = Operator(operator, Double.parseDouble(expression.substring(startIdx,idx)), Double.parseDouble(expression.substring(idx+1, expression.length())));

                     if (x == (int)x) {
                    	 expression = result.getText().substring(0,startIdx) + Integer.toString((int)x);
                     }else {
                    	 expression = result.getText().substring(0,startIdx) + Double.toString(x) ;
                     }
                  result.setText(expression);
                  
               }else {
                  first = expression.substring(startIdx, idx);
                  second = expression.substring(idx+1,endIdx+1);
                  
                     double x  = Operator(operator, Double.parseDouble(first), Double.parseDouble(second));

                     if (x == (int)x) {
                    	 expression = expression.substring(0,startIdx) + Integer.toString((int)x) + expression.substring(endIdx+1, expression.length());
                     }else {
                    	 expression = expression.substring(0,startIdx) + Double.toString(x) + expression.substring(endIdx+1, expression.length()) ;
                     }
                  
                  result.setText(expression);
               }
            }else {
               result.setText(expression);
               break;
            }
         }
         
         for(int i = 0 ; i < count; i++) {
            if(expression.contains("+") || expression.contains("-")) {
               String operator = "";
               for(int j = 0; j < expression.length(); j++) {
                  if(expression.charAt(j) == '+' || expression.charAt(j) == '-') {
                     operator = expression.charAt(j)+"";
                     break;
                  }
               }
               
               int idx = expression.indexOf(operator);
               
               int startIdx = 0 ;
               int endIdx = 0;
               for(int j = idx - 1;  j >= 0; j-- ) {
                  if(isOperator(expression.charAt(j)) && operator != (expression.charAt(j)+"")) {
                     startIdx = j+1;
                     break;
                  }
               }
               for(int j = idx + 1; j < expression.length(); j++) {
                  if(isOperator(expression.charAt(j)) && operator != (expression.charAt(j)+"")) {
                     endIdx = j-1 ;
                     break;
                  }
               }
               String first = "";
               String second = "";
               if( endIdx == 0) {
                     double x  = Operator(operator, Double.parseDouble(expression.substring(startIdx,idx)), Double.parseDouble(expression.substring(idx+1, expression.length())));
                     if (x == (int)x) {
                    	 expression = result.getText().substring(0,startIdx) + Integer.toString((int)x) ;
                     }else {
                    	 expression = result.getText().substring(0,startIdx) + Double.toString(x) ;
                     }
                     expression = result.getText().substring(0,startIdx) + x;

                  result.setText(expression);

               }else {
                  first = expression.substring(startIdx, idx);
                  second = expression.substring(idx+1,endIdx+1);
                  
                     double x  = Operator(operator, Double.parseDouble(first), Double.parseDouble(second));

                     if (x == (int)x) {
                    	 expression = expression.substring(0,startIdx) + Integer.toString((int)x) + expression.substring(endIdx+1,expression.length()) ;
                     }else {
                    	 expression = expression.substring(0,startIdx) + Double.toString(x) + expression.substring(endIdx+1, expression.length());
                     }

                  result.setText(expression);
               }
            }
         }
         
         if(result.getText().charAt(result.getText().indexOf(".")+1) == '0' && result.getText().length()-1 == result.getText().indexOf(".")+1) {
        	 result.setText(result.getText().substring(0,result.getText().indexOf(".")));
         }

         
      }
      
   }
   
   public boolean isOperator(char c ) {
      if(c == '%' || c == 'X' || c == '¡À' || c == '+' || c == '-') {
         return true;
      }else {
         return false;
      }
   }
   
   public int compare(char c) {
      if(c == '%' || c == 'X' || c == '¡À') {
         return 2;
      }else if(c == '+' || c == '-') {
         return 1;
      }else {
         return 0;
      }
   }
   
   
   public double Operator(String c, double x1 , double x2) {
      
	  if(c.equals("X")) {
	      return x1 * x2;
	  }else if(c.equals("¡À")) {
	      return x1 / x2;
	  }else if(c.equals("%")) {
	      return x1 % x2; 
	  }else if (c.equals("+")) {
		  return x1 + x2; 
	  }else if( c.equals("-")) {
	      return x1 - x2;
      }else {
          return 0;
      }
  
   }
   
   
}