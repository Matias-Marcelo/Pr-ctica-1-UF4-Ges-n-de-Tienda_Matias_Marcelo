package model;
//Create abstract class Person on package model:
public abstract class Person   {
	
	//Method name with parameter String client_sale and a void constructor.
	
	public Person(String client_sale) {
		// TODO Auto-generated constructor stub
		this.name = client_sale;
	}
	public Person() {
		
	}
	
	//Attribute protected name
	protected String name;
	
}
