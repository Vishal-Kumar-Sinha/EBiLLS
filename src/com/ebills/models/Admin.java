package com.ebills.models;

import java.util.Scanner;

public class Admin {
	private int id=0;
	private String name="";
	private String phoneno="";
	private String emailid="";
	private String password="";
	
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
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
	
	public void readAdmin() {
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
		return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", emailid='" + emailid + '\'' +
                ", password='" + password + '\'' +
                '}';
	}
}
