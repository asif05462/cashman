package com.wns.cashman.service.impl;

public class ATMWithdrawal {
	

	
		

	    /** The Constant Currency Denominations. */
	    protected static final int[] currDenom = { 200, 150, 110, 100, 80, 70, 60, 50, 40, 20 };

	    /** The Number of Currencies of each type */
	    protected static int[] currDenomLeftNo = { 10, 10, 10,10, 10, 10,10, 10, 10, 10};
	    /** The count. */
	    protected int[] count = { 0, 0, 0,0, 0, 0,0, 0, 0,0};
	    
	    protected static int totalCashLeft;
	    static {
	        calcTotalCashLeft();
	    }

	    public static void calcTotalCashLeft() {
	        for (int i = 0; i < currDenom.length; i++) {
	        	totalCashLeft = totalCashLeft + currDenom[i] * currDenomLeftNo[i];
	        }
	    }

	    public ATMWithdrawal() {

	    }

	    public synchronized void withdrawCash(int amount) {
	    	System.out.println("Withdrwing "+amount);
	    	boolean trxSuccess = true;
	        if (amount <= totalCashLeft) {
	            for (int i = 0; i < currDenom.length; i++) {
	            	
	                if (currDenom[i] <= amount) {
	                	//If the amount is less than the currDenom[i] then that particular denomination cannot be dispensed                	
	                    int noteCount = amount / currDenom[i];
	                    if (currDenomLeftNo[i] > 0) {
	                    	//To check whether the ATM Vault is left with the currency denomination under iteration
	                        //If the Note Count is greater than the number of notes in ATM vault for that particular denomination then utilize all of them 
	                        
	                    	count[i] = noteCount >= currDenomLeftNo[i] ? currDenomLeftNo[i] : noteCount;
	                        
	                    	currDenomLeftNo[i] = currDenomLeftNo[i] - count[i];
	                        
	                    	//Deduct the total corpus left in the ATM Vault with the cash being dispensed in this iteration
	                        
	                    	totalCashLeft = totalCashLeft - (count[i] * currDenom[i]);
	                        //Calculate the amount that need to be addressed in the next iterations
	                        amount = amount - (count[i] * currDenom[i]);
	                    }
	                    else {
	                    	continue;
	                    }
	                }
//	                else {
//	                	trxSuccess = false;
//	                	revertDeduction();
//	                	
//	                }
	            }
	            
	            if(amount>0) {
	            	revertDeduction();
	            }
	           
	            displayNotesDispensed();
	            displayLeftNotes();
	            

	        } else {
	            System.out.println("Unable to dispense cash at this moment for this big amount");
	        }

	    }
	    
	    private void revertDeduction() {
	        for (int i = 0; i < count.length; i++) {
	            if (count[i] != 0) {
	            	currDenomLeftNo[i] = currDenomLeftNo[i] + count[i];
	            	count[i] = 0;
	            }
	            
	        }
	        calcTotalCashLeft();
	        System.out.println("Unable to dispense cash is combination");
	    }

	    private void displayNotesDispensed() {
	        for (int i = 0; i < count.length; i++) {
	            if (count[i] != 0) {
	                System.out.println(currDenom[i] + " * " + count[i] + " = " + (currDenom[i] * count[i]));
	            }
	        }
	    }

	    private void displayLeftNotes() {
	        for (int i = 0; i < currDenom.length; i++) {
	            System.out.println("Notes of " + currDenom[i] + " left are " + currDenomLeftNo[i]);
	        }

	    }

	    public static void main(String[] args) {
	        new ATMWithdrawal().withdrawCash(110);
	        /*System.out.println("\n");
	        System.out.println("------------------------NEXT TRX----------------------------");
	        new AtmWithdrawal().withdrawCash(120);
	        System.out.println("\n");
	        System.out.println("------------------------NEXT TRX----------------------------");
	        new AtmWithdrawal().withdrawCash(130);
	        System.out.println("\n");
	        System.out.println("------------------------NEXT TRX----------------------------");
	        new AtmWithdrawal1().withdrawCash(120);
	        System.out.println("\n");
	        System.out.println("------------------------NEXT TRX----------------------------");
	        new AtmWithdrawal1().withdrawCash(170);
	        System.out.println("\n");
	        System.out.println("------------------------NEXT TRX----------------------------");
	        new AtmWithdrawal1().withdrawCash(120);
	        System.out.println("\n");
	        System.out.println("------------------------NEXT TRX----------------------------");
	        new AtmWithdrawal1().withdrawCash(120);
	    */}




	


}
