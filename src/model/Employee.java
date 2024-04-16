package model;

import main.Logable;
//Create class employee on package model, extended of abstract class Person, also implemented interface Logable
public class Employee extends Person implements Logable{
	//Attribute private int employeeId
	private int employeeId;

	//Implement method login, if user is 123 and password = test then return true, else return false
	@Override
	public boolean login(int user, String password) {
		// TODO Auto-generated method stub
		if(user == 123 && password.equalsIgnoreCase("test")) {
			
			return true;
		}else {
			
			return false;
		}
	}
	//Final Constants:
	final static int USER = 123;
	final static String PASSWORD = "test";
	
	//public boolean login(int user, String password) {
		
	//	return false;
		
		
	//}

}

