package model;

import main.Payable;

//Create clase client extended of abstract class Person and also implemented of interface Payable
public class Client extends Person implements Payable{
	//Attributes memberId, balance(Amount)
	private int memberId;
	private Amount balance;
	
	//Final constants 
	final static int MEMBER_ID = 456;
	final static double BALANCE = 50.00;

	//Constructor client
	public Client(String client_sale) {
		// TODO Auto-generated constructor stub
		super(client_sale);
		this.balance = new Amount (BALANCE);
		this.memberId = MEMBER_ID;
	}

	
	//Method pay, receives the amount of the sale upfront, subtract from initial balance, if final balance is positive it returns true, otherwise false
	@Override
	public boolean pay(Amount amount) {
		
		double resultadoFinal = 0.0;
		resultadoFinal = BALANCE - amount.getValue();
		
		if (resultadoFinal >= 0) {
			Amount test = new Amount(resultadoFinal);
			System.out.println("Dinero restante: "+test);
 			return true;

 		}else {
 			return false;
 		}
		
        // Check if the amount to be paid is greater than the balance
//        if (amount.getValue() > balance.getValue()) {
//        	
//        	double resultadoFinal = 0.0;
//        	
//        	resultadoFinal = BALANCE - amount.getValue();
//            double sueldo = amount.getValue() - balance.getValue();
//            System.out.println("Cliente debe: -" + sueldo);
//            return false; // Insufficient balance
//        } else {
//            // Subtract the payment amount from the balance
//            balance = new Amount(balance.getValue() - amount.getValue());
//            // Check if the balance after payment is positive
//            System.out.println("Saldo cliente: " + balance);
//            return balance.getValue() >= 0;
//        }
		// TODO Auto-generated method stub
	
	}


	@Override
	public String toString() {
		return "Client [name=" + name + "]";
	}


	public int getMemberId() {
		return memberId;
	}


	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}


	public Amount getBalance() {
		return balance;
	}


	public void setBalance(Amount balance) {
		this.balance = balance;
	}



	




	
}
