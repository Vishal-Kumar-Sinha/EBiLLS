package com.ebills.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.ebills.models.Customer;

public class CustomerCURD {
	private Connection connection=null;
	
	public CustomerCURD(Connection connection) {
		this.connection=connection;
	}
	
	public boolean NewCustomer(Customer customer) {
		try {
			customer.readCustomer();
			String query="{CALL Insertcustomer(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setString(1, customer.getName());
            ct.setString(2, customer.getAddress());
            ct.setString(3, customer.getPhone());
            ct.setString(4, customer.getEmail());
            ct.setString(5, customer.getPassword());
            ct.setString(6, customer.getCondate());
            ct.setString(7, customer.getContype());
            ct.executeQuery();
			return true;
		}
		catch(Exception e) {
			System.out.println("Error adding book to database");
            e.printStackTrace();
            return false;
		}
	}
	
	public void getOneCustomer(int tid) {
		try{
			String query = "{CALL Onecustomer(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ResultSet rs = ct.executeQuery();
            if(rs.next()) {
            	System.out.println("---------------------------------------------------");
            	System.out.println("Customer ID :: "+rs.getInt("customerid"));
            	System.out.println("Customer name :: "+rs.getString("name"));
            	System.out.println("Customer address :: "+rs.getString("address"));
            	System.out.println("Customer phone number :: "+rs.getString("phoneno"));
            	System.out.println("Customer email-ID :: "+rs.getString("emailid"));
            	System.out.println("Customer password :: "+rs.getString("password"));
            	System.out.println("Customer connection date :: "+rs.getString("connectiondate"));
            	System.out.println("Customer connection type :: "+rs.getString("connectiontype"));
            	System.out.println("---------------------------------------------------");
            }
            else {
            	System.out.println("Data not found...!");
            }
		}
		catch(SQLException sqe) {
			System.out.println("Error from database");
            sqe.printStackTrace();
		}
	}
	
	public void getAllCustomer() {
		try{
			String query = "{CALL Displaycustomer}";
            CallableStatement ct = connection.prepareCall(query);
            ResultSet rs = ct.executeQuery();
            System.out.println("---------------------------------------------------");
            while(rs.next()) {
            	System.out.println("Customer ID :: "+rs.getInt("customerid"));
            	System.out.println("Customer name :: "+rs.getString("name"));
            	System.out.println("Customer address :: "+rs.getString("address"));
            	System.out.println("Customer phone number :: "+rs.getString("phoneno"));
            	System.out.println("Customer email-ID :: "+rs.getString("emailid"));
            	System.out.println("Customer password :: "+rs.getString("password"));
            	System.out.println("Customer connection date :: "+rs.getString("connectiondate"));
            	System.out.println("Customer connection type :: "+rs.getString("connectiontype"));
            	System.out.println("---------------------------------------------------");
            }
		}
		catch(SQLException sqe) {
			System.out.println("Error from database");
            sqe.printStackTrace();
		}
	}
	
	public void updateCustomer(Customer customer) {
		Scanner s=new Scanner(System.in);
		System.out.print("a. Change password\nb. Update all details\nEnter choice :: ");
		char ch=s.next().charAt(0);
		System.out.print("Enter customer id :: ");
		int tid=s.nextInt();
		
		switch(ch) {
			case 'a':
				try {
					System.out.print("Enter new password :: ");
					String temp=s.next();
		            String query = "{CALL Changecustomerpassword(?, ?)}";
		            CallableStatement ct = connection.prepareCall(query);
		            ct.setInt(1, tid);
		            ct.setString(2, temp);
		            ct.executeUpdate();
		        }
				catch(Exception e) {
		            System.out.println("Error updating Password in database");
		            e.printStackTrace();
		        }
				s.close();
				break;
			case 'b':
				try {
					String query="{CALL Updatecustomer(?, ?, ?, ?, ?, ?, ?, ?)}";
		            CallableStatement ct = connection.prepareCall(query);
		            customer.readCustomer();
		            ct.setInt(1, tid);
		            ct.setString(2, customer.getName());
		            ct.setString(3, customer.getAddress());
		            ct.setString(4, customer.getPhone());
		            ct.setString(5, customer.getEmail());
		            ct.setString(6, customer.getPassword());
		            ct.setString(7, customer.getCondate());
		            ct.setString(8, customer.getContype());
		            ct.executeUpdate();
				}
				catch(Exception e) {
					System.out.println("Error in updating database");
		            e.printStackTrace();
				}
				break;
			default:
				System.out.println("Invalid choice");
		}
	}
	
	public void updateCustomer(Customer customer, int tid) {
		Scanner s=new Scanner(System.in);
		System.out.print("a. Change password\nb. Update all details\nEnter choice :: ");
		char ch=s.next().charAt(0);
		
		switch(ch) {
			case 'a':
				try {
					System.out.print("Enter new password :: ");
					String temp=s.next();
		            String query = "{CALL Changecustomerpassword(?, ?)}";
		            CallableStatement ct = connection.prepareCall(query);
		            ct.setInt(1, tid);
		            ct.setString(2, temp);
		            ct.executeUpdate();
		        }
				catch(Exception e) {
		            System.out.println("Error updating Password in database");
		            e.printStackTrace();
		        }
				s.close();
				break;
			case 'b':
				try {
					String query="{CALL Updatecustomer2(?, ?, ?, ?, ?)}";
		            CallableStatement ct = connection.prepareCall(query);
		            customer.readCustomer2();
		            ct.setInt(1, tid);
		            ct.setString(2, customer.getName());
		            ct.setString(3, customer.getPhone());
		            ct.setString(4, customer.getEmail());
		            ct.setString(5, customer.getPassword());
		            ct.executeUpdate();
				}
				catch(Exception e) {
					System.out.println("Error in updating database");
		            e.printStackTrace();
				}
				break;
			default:
				System.out.println("Invalid choice");
		}
	}
	
	public void deleteCustomer(int tid) {
		try{
			String query = "{CALL Deletecustomer(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ct.executeQuery();
		}
		catch(Exception e) {
			System.out.println("Error in deleting customer");
            e.printStackTrace();
		}
	}
	
	public int loginCustomer() {
		try{
			Scanner s=new Scanner(System.in);
			System.out.print("Enter email-ID :: ");
			String mail=s.next();
			System.out.print("Enter password :: ");
			String pass=s.next();
			String query = "{? = CALL Customerlogin(?, ?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setString(2, mail);
            ct.setString(3, pass);
            ct.registerOutParameter(1, Types.INTEGER);
            ct.execute();
            int ans=ct.getInt(1);
            s.close();
            if(ans==0) {
            	System.out.println("Invalid login");
            	return 0;
            }
            else {
            	System.out.println("Login successful...!\nWelcome... ");
            	return ans;
            }
		}
		catch(Exception e) {
			System.out.println("Error in Login...Try again");
            e.printStackTrace();
            return 0;
		}
	}
	
}
