/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policymanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author s6085604
 */

class Policy {
    
    private String menuChoice;
    private String name;
    private String refNum;
    private int gadgetNumber;
    private double gadgetValue;
    private double paymentExcess;
    private String term;
    private double premium;
    private String date;
    private String menu;
    
    Policy()
    {
        
    }
    
    Policy (String n, String r, int q, double v, double e, String t, String d)
    {
        name = n;
        refNum = r;
        gadgetNumber = q;
        gadgetValue = v;
        paymentExcess = e;
        term = t;
        date = d;
        
    }
    
    
    String getName()
    {
        return name;
    }
    
    String getNumber()
    {
        return refNum;
    }
    
    int getGadget()
    {
        return gadgetNumber;
    }
    
    double getGadgetValue()
    {
        return gadgetValue;
    }
    
    double getExcess()
    {
        return paymentExcess;
    }
    
    String getTerms()
    {
        return term;
    }
    
    double getPremium()
    {
        return premium;
    }
    
    String getDate()
    {
        return date;
    }
    
    void setName(String n)
    {
        name = n;
    }
    
    void setRefNum (String r)
    {
        refNum = r;
    }
    
    void setGadgetNumber(int q)
    {
        gadgetNumber = q;
    }
    
    void setGadgetValue(double v)
    {
        gadgetValue = v;
    }
    
    void setExcess(double e)
    {
        paymentExcess = e;
    }
    
    void setTerm(String t)
    {
        term = t;
    }
    void setPremium()
    {
        if (gadgetValue <=550)
    	{
        	if (gadgetNumber == 1)
        	{
            	premium = 4.99;
        	}
        	else if (gadgetNumber >= 2 && gadgetNumber <=3)
        	{
            	premium = 9.99;
        	}
        	else if (gadgetNumber >= 4 && gadgetNumber <=5 )
        	{
            	premium = 14.99;
        	}
    	}
   	 
    	else if (gadgetValue > 550 && gadgetValue <= 800)
    	{
       	if (gadgetNumber == 1)
        	{
            	premium = 6.15;
        	}
        	else if (gadgetNumber >= 2 && gadgetNumber <=3)
        	{
            	premium = 12.35;
        	}
        	else if (gadgetNumber >= 4 && gadgetNumber <=5 )
        	{
            	premium = 18.60;
        	}
    	}
    	else if (gadgetValue >=800 && gadgetValue <=1000)
    	{
        	if (gadgetNumber == 1)
        	{
            	premium = 7.30;
        	}
        	else if (gadgetNumber >= 2 && gadgetNumber <=3)
        	{
            	premium = 14.55;
        	}
        	else if (gadgetNumber >= 4 && gadgetNumber <=5 )
        	{
            	premium = 21.82;
        	}
                else if  (gadgetNumber == 6)
                 {
                premium = 22;        
                 }
    	}
    	else
    	{
       	 
    	}
        
         //code to calculate the excess on the gadgets if it's needed
        if (paymentExcess == 70 )
    	{
            premium = premium * 0.8;
    	}
    	if (paymentExcess >= 60 )
    	{
            premium = premium * 0.85;
    	}
    	else if (paymentExcess >= 50 )
    	{
            premium = premium * 0.9;
    	}
    	else if (paymentExcess >=40)
    	{
            premium = premium * 0.95;
    	}
        
        //code to calculate the annual premium if needed
        if (term.equals("A"))
    	{
            premium = premium * 0.9;
            premium = premium * 12;
    	}
  
    }//end of set premium
    void setDate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        date = sdf.format(cal.getTime());
    }  //end of set date           
        
         
}//end of class policy1
