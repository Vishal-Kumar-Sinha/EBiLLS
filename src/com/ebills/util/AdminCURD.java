package com.ebills.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.ebills.models.Admin;

public class AdminCURD {
	private Connection connection=null;
	
	public AdminCURD(Connection connection) {
		this.connection=connection;
	}
	
	public boolean NewAdmin(Admin admin) {
		try {
			admin.readAdmin();
			String query="{CALL Insertadmin(?, ?, ?, ?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setString(1, admin.getName());
            ct.setString(2, admin.getPhone());
            ct.setString(3, admin.getEmail());
            ct.setString(4, admin.getPassword());
            ct.executeQuery();
			return true;
		}
		catch(Exception e) {
			System.out.println("Error adding book to database");
            e.printStackTrace();
            return false;
		}
	}
	
	public void getOneAdmin(int tid) {
		try{
			String query = "{CALL Oneadmin(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ResultSet rs = ct.executeQuery();
            if(rs.next()) {
            	System.out.println("---------------------------------------------------");
            	System.out.println("Admin ID :: "+rs.getInt("id"));
            	System.out.println("Admin name :: "+rs.getString("name"));
            	System.out.println("Admin phone number :: "+rs.getString("phoneno"));
            	System.out.println("Admin email-ID :: "+rs.getString("emailid"));
            	System.out.println("Admin password :: "+rs.getString("password"));
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
	
	public void getAllAdmin() {
		try{
			String query = "{CALL Displayadmin}";
            CallableStatement ct = connection.prepareCall(query);
            ResultSet rs = ct.executeQuery();
            System.out.println("---------------------------------------------------");
            while(rs.next()) {
            	System.out.println("Admin ID :: "+rs.getInt("id"));
            	System.out.println("Admin name :: "+rs.getString("name"));
            	System.out.println("Admin phone number :: "+rs.getString("phoneno"));
            	System.out.println("Admin email-ID :: "+rs.getString("emailid"));
            	System.out.println("Admin password :: "+rs.getString("password"));
            	System.out.println("---------------------------------------------------");
            }
		}
		catch(SQLException sqe) {
			System.out.println("Error from database");
            sqe.printStackTrace();
		}
	}
	
	public void updateAdmin(Admin admin) {
		Scanner s=new Scanner(System.in);
		System.out.print("a. Change password\nb. Update all details\nEnter choice :: ");
		char ch=s.next().charAt(0);
		System.out.print("Enter Admin id :: ");
		int tid=s.nextInt();
		
		switch(ch) {
			case 'a':
				try {
					System.out.print("Enter new password :: ");
					String temp=s.next();
		            String query = "{CALL Changeadminpassword(?, ?)}";
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
					String query="{CALL Updateadmin(?, ?, ?, ?, ?)}";
		            CallableStatement ct = connection.prepareCall(query);
		            admin.readAdmin();
		            ct.setInt(1, tid);
		            ct.setString(2, admin.getName());
		            ct.setString(3, admin.getPhone());
		            ct.setString(4, admin.getEmail());
		            ct.setString(5, admin.getPassword());
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
	
	public void deleteAdmin(int tid) {
		try{
			String query = "{CALL Deleteadmin(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ct.executeQuery();
		}
		catch(Exception e) {
			System.out.println("Error in deleting admin");
            e.printStackTrace();
		}
	}
	
	public int loginAdmin() {
		try{
			Scanner s=new Scanner(System.in);
			System.out.print("Enter email-ID :: ");
			String mail=s.next();
			System.out.print("Enter password :: ");
			String pass=s.next();
			String query = "{? = CALL Adminlogin(?, ?)}";
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
