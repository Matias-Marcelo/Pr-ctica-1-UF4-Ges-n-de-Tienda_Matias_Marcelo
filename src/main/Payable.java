package main;

import model.Amount;
//Create new interface called playable
public interface Payable {
	
	//Method with parameter input amount, return boolean
	public boolean pay(Amount amount); 
	
	

}
