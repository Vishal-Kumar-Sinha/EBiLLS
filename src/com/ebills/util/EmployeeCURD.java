package com.ebills.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.ebills.models.Employee;

public class EmployeeCURD {
	private Connection connection=null;
	
	public EmployeeCURD(Connection connection) {
		this.connection=connection;
	}
	
	public boolean NewEmployee(Employee employee) {
		try {
			employee.readEmployee();
			String query="{CALL Insertemployee(?, ?, ?, ?, ?, ?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setString(1, employee.getName());
            ct.setString(2, employee.getAddress());
            ct.setString(3, employee.getPhone());
            ct.setString(4, employee.getEmail());
            ct.setString(5, employee.getPassword());
            ct.setString(6, employee.getAadhar());
            ct.executeQuery();
			return true;
		}
		catch(Exception e) {
			System.out.println("Error adding book to database");
            e.printStackTrace();
            return false;
		}
	}
	
	public void getOneEmployee(int tid) {
		try{
			String query = "{CALL Oneemployee(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ResultSet rs = ct.executeQuery();
            if(rs.next()) {
            	System.out.println("---------------------------------------------------");
            	System.out.println("Employee ID :: "+rs.getInt("empid"));
            	System.out.println("Employee name :: "+rs.getString("name"));
            	System.out.println("Employee address :: "+rs.getString("address"));
            	System.out.println("Employee phone number :: "+rs.getString("phoneno"));
            	System.out.println("Employee email-ID :: "+rs.getString("emailid"));
            	System.out.println("Employee password :: "+rs.getString("password"));
            	System.out.println("Employee aadhar number :: "+rs.getString("aadharno"));
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
	
	public void getAllEmployee() {
		try{
			String query = "{CALL Displayemployee}";
            CallableStatement ct = connection.prepareCall(query);
            ResultSet rs = ct.executeQuery();
            System.out.println("---------------------------------------------------");
            while(rs.next()) {
            	System.out.println("Employee ID :: "+rs.getInt("empid"));
            	System.out.println("Employee name :: "+rs.getString("name"));
            	System.out.println("Employee address :: "+rs.getString("address"));
            	System.out.println("Employee phone number :: "+rs.getString("phoneno"));
            	System.out.println("Employee email-ID :: "+rs.getString("emailid"));
            	System.out.println("Employee password :: "+rs.getString("password"));
            	System.out.println("Employee aadhar number :: "+rs.getString("aadharno"));
            	System.out.println("---------------------------------------------------");
            }
		}
		catch(SQLException sqe) {
			System.out.println("Error from database");
            sqe.printStackTrace();
		}
	}
	
	public void updateEmployee(Employee employee, int tid) {
		Scanner s=new Scanner(System.in);
		System.out.print("a. Change password\nb. Update all details\nEnter choice :: ");
		char ch=s.next().charAt(0);
		
		switch(ch) {
			case 'a':
				try {
					System.out.print("Enter new password :: ");
					String temp=s.next();
		            String query = "{CALL Changeemployeepassword(?, ?)}";
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
					String query="{CALL Updateemployee(?, ?, ?, ?, ?, ?, ?)}";
		            CallableStatement ct = connection.prepareCall(query);
		            employee.readEmployee();
		            ct.setInt(1, tid);
		            ct.setString(2, employee.getName());
		            ct.setString(3, employee.getAddress());
		            ct.setString(4, employee.getPhone());
		            ct.setString(5, employee.getEmail());
		            ct.setString(6, employee.getPassword());
		            ct.setString(7, employee.getAadhar());
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
	
	public void deleteEmployee(int tid) {
		try{
			String query = "{CALL Deleteemployee(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ct.executeQuery();
		}
		catch(Exception e) {
			System.out.println("Error in deleting employee");
            e.printStackTrace();
		}
	}
	
	public int loginEmployee() {
		try{
			Scanner s=new Scanner(System.in);
			System.out.print("Enter email-ID :: ");
			String mail=s.next();
			System.out.print("Enter password :: ");
			String pass=s.next();
			String query = "{? = CALL Employeelogin(?, ?)}";
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
