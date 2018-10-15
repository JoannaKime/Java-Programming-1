/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policymanager;
 
import java.util.Scanner;
import java.io.*;


 
/**
 *
 * @author s6085604
 */
public class PolicyManager {
 
    //holds the main scanner allowing input from the client
    public static Scanner keyboard = new Scanner(System.in);
   
    public static void main(String[] args) {
        //calls the main menu
        displayMenu();
    }//end of main
   
    static void displayMenu()
    { //Shows the main menu which the client has to choose from
        String mainMenu;
        System.out.println("Please choose an option from below");
        System.out.println(" ");
        System.out.println("1. Enter new policy");
        System.out.println("2. Display summary of policies");
        System.out.println("3. Display summary of policies for selected month");
        System.out.println("4. Find and display policy");
        System.out.println("0. Exit");
        //takes the input from the client
        mainMenu = keyboard.nextLine();
       
        //validates the clients choice ensuring it's valid
        while (!(mainMenu.matches("[0-4]{1}")))
        {
            System.out.println("Invalid option, please try again: ");
            mainMenu = keyboard.nextLine();
        }
        switch(mainMenu)
        {
            case "1":
                newPolicy();
                break;
            case "2":
                fileSummary();
                break;
            case "3":
                monthSummary();
                break;
            case "4":
                searchFile();
                break;
            case "0":
                System.out.println("Exiting...");
                break;
                             
        }//end of switch
    }//emd of displau menu
   
    static void newPolicy()
    {
       //holds the methods that call the getters and setters in the systems, allowing the attributes to be set for them
       Policy newPolicy = new Policy();
       newPolicy.setName(getName());
       newPolicy.setRefNum(getNumber());
       newPolicy.setGadgetNumber(getGadget());
       newPolicy.setGadgetValue(getGadgetValue());
       newPolicy.setExcess(getExcess());
       newPolicy.setTerm(getTerms());
       newPolicy.setDate();
       newPolicy.setPremium();
       
       writeToFile(newPolicy.getDate(), newPolicy.getNumber(), newPolicy.getGadget(),newPolicy.getGadgetValue(), newPolicy.getExcess(), newPolicy.getPremium(), newPolicy.getTerms(), newPolicy.getName());
       displayPolicy(newPolicy.getDate(), newPolicy.getNumber(), newPolicy.getGadget(),newPolicy.getGadgetValue(), newPolicy.getExcess(), newPolicy.getPremium(), newPolicy.getTerms(), newPolicy.getName());
       
              
    }//end of new policy
   
     static String getName()
    //gets the name from the client
    {
        System.out.println("Please enter your name (e.g. John Smith) : "); //20 character maximum
        String name = keyboard.nextLine();
    //validates and ensures the name doesn't exceed 20 characters
        while (name.length() <1 || name.length() >20)
        {
            System.out.println("Name must be between 1 and 20 chars long");
            System.out.println("Name inputted is too long. Please re-enter staff name (e.g. John Smith): ");
            name = keyboard.nextLine();
        }
        return name;
    }//end of getName string
   
    static String getNumber()
    //gets the customer reference number from the client
    {
        System.out.println("Please enter your customer reference number (e.g. AB123C) : "); //validate
        String customerNum = keyboard.nextLine().toUpperCase();
    //checks that the customer reference number is of the correct length and formatted in the correct way
        boolean valid = false;  
        while (!valid)
        {
            valid = true;  
            if (customerNum.length() != 6)
            {
                System.out.println("Customer reference number should be 6 charaters long (e.g. AB123C)");
                valid = false;
            }
            else if (!Character.isLetter(customerNum.charAt(0)) || !Character.isLetter(customerNum.charAt(1)) ||
                     !Character.isDigit(customerNum.charAt(2)) || !Character.isDigit(customerNum.charAt(3)) ||
                     !Character.isDigit(customerNum.charAt(4)) || !Character.isLetter(customerNum.charAt(5)))
            {
                System.out.println("Customer reference number should be 2 letters followed by 3 digits, followed by a single letter (e.g. AB123C)" );
                valid = false;
            }
            if (!valid)
            {
                System.out.print("Please enter customer reference number (e.g. AB123C) : ");
                customerNum = keyboard.nextLine();
            }
        }
        return customerNum;
    }//end of getNumber string
   
    static int getGadget()
    //gets the total number of gadgets that the client is buying
    {
     String quantity;
     
     System.out.println("Please enter your total number of gadgets : ");
     
     quantity = keyboard.next();
     
    while(!(quantity.matches(".*[0-9].*")))
    {
        System.out.println("You have inputted an invalled data type");
        System.out.println("Please try again");
    }
    //validates and ensures that only positive amounts of gadgets can be bought and that the client can't miss the feild
    while (Integer.parseInt(quantity) <1)
    {
       System.out.println("Please enter your total number of gadgets : ");
        quantity = keyboard.next();
    }
   
    return Integer.parseInt(quantity);
    }//end of getGadget() string
   
    static double getGadgetValue()
    //gets the total of the most expensive gadget the client has bought
    {
        System.out.println("Please enter the value of your most expensive gadget (e.g. £960) : ");
        double gadgetValue;
        int gadgetLimit;
        gadgetValue = keyboard.nextDouble();
       
    //ensures the value isn't below 0
        while (gadgetValue <= 0)
        {
            System.out.println("A negative value has been entered.");
            System.out.print("Please enter a positive value : ");
            gadgetValue= keyboard.nextDouble();
         
        }
    //ensures the cost doesn't exceed £1000.00
        while (gadgetValue > 1000.00)
        {
            System.out.println("Please enter a value below £1000");
            gadgetValue = keyboard.nextDouble();
        }
        return gadgetValue;
    }
   
    static double getExcess()
    //gets the amount of excess the client would like to pay
    {
        System.out.println("Excess must be between £30.00 and £70.00");
        System.out.println("Please enter how much excess you would like to pay (e.g. £30.00) : ");
        double excess = keyboard.nextDouble();
    //validation to ensure that the excess doesn't go below or above the boundaries
        while (excess <30.00 && excess > 70.00)
        {
            System.out.println("Please enter a value above £30.00 and below or equal to £70.00");
            excess = keyboard.nextDouble();
        }
       
        return excess;
    }//end of excess string
   
    static String getTerms()
    // gets the payment terms from the client
    {
        char payment;
        String term;
       
     
        System.out.print("Would you like to pay monthly (M) or annually (A) : ");
        payment = Character.toUpperCase(keyboard.next().charAt(0));
     
        //ensures the correct characters have been entered
        while (payment != 'A' && payment != 'M')
        {
            System.out.print("Invalid entry, please try again (A or M) : ");
            payment = Character.toUpperCase(keyboard.next().charAt(0));
             
        }
     
        //asigns the correct term to the input the client gave
        if (payment == 'A')
        {
            term = "Annual";
        }
        else
        {
            term = "Monthly";
        }
        return term;
     
    }//end of get terms string
    static void writeToFile(String date, String reference, int gadgetNum, double gadgetCost, double excessCost, double premium, String paymentType, String name)
    {
        PrintWriter output = null;
 
        File policy = new File("policy.txt");
 
        // checks to see if the file the client requested exists
       
        
            try {
                FileWriter fw = new FileWriter(policy, true);
                output = new PrintWriter(fw);
               
            } catch (FileNotFoundException e)
            {
                System.out.println("The file can not be created. Exiting...");
                System.exit(0);  // this closes the system
            } catch (IOException ex)
            {
                System.out.println("The file can not be created. Exiting...");
                System.exit(0);
            }
            if (gadgetNum > 5 || gadgetCost > 1000)
            {
                premium = -1;
                paymentType = "r";
            }
            else
            {
                premium = premium * 100;
            }
            output.print(date + "\t");
            output.print(reference + "\t");
            output.print(+ gadgetNum + "\t");
            output.print((int)gadgetCost + "\t");
            output.print((int)excessCost + "\t");
            output.print((int)(premium) + "\t");
            output.print(paymentType + "\t");
            output.print(name + "\t");
            output.close();
      
    }//end of write to file
        
    static void fileSummary()
    {
        displaySummary(1);
    }//end of file summary
    static void monthSummary()
    {
        displaySummary(2);        
    }//end of month summary
    static void searchFile()
    {
        displaySummary(3);
    }//end of search file
    static void displaySummary(int option)
    {
        String choice;
        String fileName;
        //displays the new menu
        System.out.println("1. Current Policies");
        System.out.println("2. Archived Policies");
        System.out.println("0. Exit");
        System.out.println("");
        System.out.println("Please choose one of the policies:");
        choice = keyboard.nextLine();
        
        
        while(!(choice.matches("[0-2]{1}")))
        {
            System.out.print("Invalid choice, please try again: ");
            choice = keyboard.nextLine();
        }
       
        switch(choice)//sees which choice the client made
        {
            case "1":
                fileName = "policy.txt";
                readFromFile(fileName, option);
                break;
            case "2":
                fileName = "archive.txt";
                readFromFile(fileName, option);
                break;
            case "0":
                displayMenu();
                break;
        }//end of switch
    }//end of display summary

    
     static void readFromFile(String file, int option)
    {
        //variables that are used to read the file into the computer
        int total = 0;
        int monthTotal = 0;
        int jan = 0;
        int feb = 0;
        int mar = 0;
        int apr = 0;
        int may = 0;
        int jun = 0;
        int jul = 0;
        int aug = 0;
        int sep = 0;
        int oct = 0;
        int nov = 0;
        int dec = 0;
        String month = " ";
        String search = " ";
        
   //sees which option the client is coming from
        
        if (option == 2)
        {
            System.out.println("Please enter the abreviated form of the month you want e.g. Jul");
             month = keyboard.next().toLowerCase();
            while (!(month.equals ("jan") || month.equals ("feb") || month.equals ("mar") || month.equals ("apr") || month.equals ("may") || month.equals ("jun") || month.equals ("jul") || month.equals ("aug") || month.equals ("sep") || month.equals ("oct") || month.equals ("nov") || month.equals ("dec")))
            {
                System.out.println("Invalid entry please try again");
                month = keyboard.next().toLowerCase();
            }
        
        }// end of if
        else if (option == 3)
        {
            System.out.println("Please enter search");    
            search = keyboard.next().toLowerCase();
        }//end of else if
        
        try 
        {
            File currentFile = new File (file);
           
            if (currentFile.exists())
            { 
                FileReader fReader = new FileReader(currentFile);
                LineNumberReader lnr = new LineNumberReader(fReader);
                
                while (lnr.readLine() !=null)
                {
                    total++;
                }
            
            }
            else
            {
                System.out.println("File does not exist, please try again");
            }
        }catch (IOException e) {}//end of try
        
        Scanner input = null;
                
        try{
            
            input = new Scanner (new File (file));
            
        }catch (FileNotFoundException e){
            System.out.println("File does not exist please try again");
            System.exit(1);
        }
        int accepted = 0;
        int quantity = 0;
        double premium = 0;
        
     //checks that the file has the data needed    
    while (input.hasNext())
    {
        String d = input.next();
        String r = input.next();
        int q = input.nextInt();
        int v = input.nextInt();
        int p = input.nextInt();
        int e = input.nextInt();
        String t = input.next();
        String n = input.nextLine();
        
        //sees which option the client has come from 
        if (option == 1)
        {
            //checks to see if the system has previously rejected the policy 
            if (!(String.valueOf (p).equals("-1")))
            {
              accepted++;
              quantity = quantity + q;  
              //sees if the terms are anual 
              if(t.equals("A"))
              {
                  //if it is anual it adds the running total to it
                  premium = premium + (p/12);
              }
              else
              {
                  //if its not anual it adds the premium to it
                 premium = premium + p;
              }
            }//end of if 
        }//end of if option 1
        
        else if (option == 2)
        {
            //sees if the date the client wants is in the month
            if (d.toLowerCase().contains(month))
            {
                monthTotal++;
                if (!(String.valueOf(p).equals("-1")))
                {
                    accepted++;
                    quantity = quantity + q;
                if (t.equals("A"))
                {
                    premium = premium + (p/12);
                }//end of A if
                else
                {
                   premium = premium + p; 
                }//end of else
                
                }//end of if equals
            }//end of if month
        }
    
     else if (option == 3)
            {
                if (r.toLowerCase().contains(search) || n.toLowerCase().contains(search))
                {
                    
                }
            
            }//end of else if option 2
        
    //checks if a user came from a ccertain option 
    if (option == 1)
    {
        if (d.contains ("Jan"))
        {
            jan++;
        }
        else if(d.contains ("Feb"))
        {
            feb++;
        }
        else if(d.contains ("Mar"))
        {
            mar++;
        }
        else if(d.contains ("Apr"))
        {
            apr++;
        }
        else if(d.contains ("May"))
        {
            may++;
        }
        else if(d.contains ("Jun"))
        {
            jun++;
        }
        else if(d.contains ("Jul"))
        {
            jul++;
        }
        else if(d.contains ("Aug"))
        {
            aug++;
        }
        else if(d.contains ("Sep"))
        {
            sep++;
        }
        else if(d.contains ("Oct"))
        {
            oct++;
        }
        else if(d.contains ("Nov"))
        {
            nov++;
        }
        else if(d.contains ("Dec"))
        {
            dec++;
        }
        }//end of while loop
    }//end of if option 1
    
    if (option == 1 || option == 2)
    {
        //changes to premium to pounds and pence
        premium = (premium / accepted / 100);
        //outputs the summary box of the policy chosen 
        System.out.println("Total number of policies: " + monthTotal);
        System.out.println("Number of items: " + quantity / accepted);
        System.out.println("Monthly premium: " + Math.round(premium*100/100));
        
        //checks which option the client came from
        if (option == 1)
        {
            System.out.println("Number of policies per month including non accepted ones: ");
            System.out.println(" ");
            System.out.printf("%-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
            System.out.println("");
            System.out.printf("%-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s",jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec);
        }
    }//end of if statement 
    }//end of read from file 
     
     static void displayPolicy(String date, String reference, int gadgetNum, double gadgetCost, double excessCost, double premium, String paymentType, String name)
     {
          String gadgetNumber = " ";
           
        if (gadgetNum == 1)
        {
            gadgetNumber = "One";
        }
        else if (gadgetNum == 2)
        {
            gadgetNumber = "Two";
        }
        else if (gadgetNum == 3)
        {
            gadgetNumber = "Three";
        }
        else if (gadgetNum == 4)
        {
            gadgetNumber = "Four";
        }
        else if (gadgetNum == 5)
        {
            gadgetNumber = "Five";
        }
        else
        {
            gadgetNumber = Integer.toString(gadgetNum);
        }
           
    //code to show the output box
        System.out.println(" ");
        System.out.println("  +=============================================+");
        System.out.println("  |                                             |");
        System.out.printf ("  |" + "%10s %-20s %14s %n", "Client: " ,name, " " + "|");
        System.out.println("  |                                             |");
        System.out.printf ("  |" + "%10s %-20s %5s %-25s %n", "Date", date, "Ref", reference + " |");
        System.out.printf ("  |" + "%10s %-19s %6s %-5s %2s %n", "Terms: ", paymentType, "Items:", gadgetNum, "|");
        System.out.printf ("  |" + "%10s £%-20.2f %13s %n", "excess: ", excessCost, " " + "|" );
        System.out.println("  |                                             |");
        //code to display any needed error messages in the clients inputs
         if (gadgetNum > 5)
        {                      
            System.out.println("  |  Your payment has been rejected due to the  |");
            System.out.println("  |    number of gadgets exceeding the limit    |");
        }
        else if (gadgetCost > 1000)
        {
            System.out.println("  |  Your payment has been rejected due to the  |");
            System.out.println("  |    value of gadgets exceeding the limit     |");
        }
        else
        {
            System.out.printf("  |" + "%9s %-16s %-10s %8s %n", "Annual ","", "Limit per" , " |");
            System.out.printf("  |" + "%10s £%-15.2f %1s %2s %8s %n", "Premium: " , premium ,"", "Gadget:" , gadgetCost + " |");
        }
         System.out.println("  |                                             |");
         System.out.println("  +=============================================+");
     }
             
    
}//end of class policy manager