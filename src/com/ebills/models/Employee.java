package com.ebills.models;

import java.util.Scanner;

public class Employee {
	private int tid=0;
	private String name="";
	private String address="";
	private String phoneno="";
	private String emailid="";
	private String password="";
	private String aadharno="";
	
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
	public String getAadhar() {
		return aadharno;
	}
	public int getEmpID() {
		return tid;
	}
	public void readEmployee() {
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
			System.out.print("Enter aadhar number :: ");
			aadharno=s.next();	
		}
		catch(Exception e) {
			System.out.println("Exception :: "+e.getMessage());
		}
	}
	
	public String toString() {
		return "Employee{" +
                "empid=" + tid +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", emailid='" + emailid + '\'' +
                ", password='" + password + '\'' +
                ", aadharno='" + aadharno + '\'' +
                '}';
	}
}