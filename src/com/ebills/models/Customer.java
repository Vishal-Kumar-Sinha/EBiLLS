package com.ebills.models;

import java.util.Scanner;

public class Customer {
	private int customerid=0;
	private String name="";
	private String address="";
	private String phoneno="";
	private String emailid="";
	private String password="";
	private String connectiondate="";
	private String connectiontype="";
	
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phoneno;
	}
	public String getEmail() {
		return emailid;
	}
	public String getPassword() {
		return password;
	}
	public String getCondate() {
		return connectiondate;
	}
	public int getCustID() {
		return customerid;
	}
	public String getContype() {
		return connectiontype;
	}
	
	public void readCustomer() {
		try(Scanner s=new Scanner(System.in)) {
			System.out.print("Enter name :: ");
			name=s.nextLine();
			System.out.print("Enter address :: ");
			address=s.nextLine();
			System.out.print("Enter phone number :: ");
			phoneno=s.next();
			System.out.print("Enter email-ID :: ");
			emailid=s.next();
			System.out.print("Enter password :: ");
			password=s.next();
			System.out.print("Enter connection date(dd/mm/yyyy) :: ");
			connectiondate=s.next();
			System.out.print("1. Domestic\n2. Commerial(LT)\n3. Industrial(HT)\nSelect connection type :: ");
			int choice=s.nextInt();
			switch(choice) {
			case 1:
				connectiontype="Domestic";
				break;
			case 2:
				connectiontype="Commerial(LT)";
				break;
			case 3:
				connectiontype="Industrial(HT)";
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
		catch(Exception e) {
			System.out.println("Exception :: "+e.getMessage());
		}
	}
	
	public void readCustomer2() {
		try(Scanner s=new Scanner(System.in)) {
			System.out.print("Enter name :: ");
			name=s.nextLine();
			System.out.print("Enter phone number :: ");
			phoneno=s.next();
			System.out.print("Enter email-ID :: ");
			emailid=s.next();
			System.out.print("Enter password :: ");
			password=s.next();
		}
		catch(Exception e) {
			System.out.println("Exception :: "+e.getMessage());
		}
	}
	
	public String toString() {
		return "Customer{" +
                "customerid=" + customerid +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", emailid='" + emailid + '\'' +
                ", password='" + password + '\'' +
                ", connectiondate='" + connectiondate + '\'' +
                ", connectionid='" + connectiontype + '\'' +
                '}';
	}
}
