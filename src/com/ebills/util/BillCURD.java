package com.ebills.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.ebills.models.Bill;

public class BillCURD {
private Connection connection=null;
	
	public BillCURD(Connection connection) {
		this.connection=connection;
	}
	private int unitconsumed=0;
	private int amount=0;
	public boolean generateBill(Bill bill, int custid) {
		try {
			String query1 = "{? = CALL Unitconsumption(?)}";
            CallableStatement ct1 = connection.prepareCall(query1);
            ct1.setInt(2, custid);
            ct1.registerOutParameter(1, Types.INTEGER);
            ct1.execute();
            unitconsumed=ct1.getInt(1);
            String query2 = "{? = CALL Connectiontype(?)}";
            CallableStatement ct2 = connection.prepareCall(query2);
            ct2.setInt(2, custid);
            ct2.registerOutParameter(1, Types.VARCHAR);
            ct2.execute();
            String ctype=ct1.getString(1).trim();
            if(unitconsumed<0) {
            	throw new Exception();
            }
            else if(unitconsumed<=50 && ctype=="Domestic") {
            	amount=(unitconsumed*2)+50;
            }
            else if((unitconsumed>50 && unitconsumed<=100) && ctype=="Domestic") {
            	amount=(int)((unitconsumed*2.5)+50);
            }
            else if((unitconsumed>100 && unitconsumed<=150) && ctype=="Domestic") {
            	amount=(int)((unitconsumed*2.75)+50);
            }
            else if((unitconsumed>150 && unitconsumed<=250) && ctype=="Domestic") {
            	amount=(int)((unitconsumed*5.25)+50);
            }
            else if((unitconsumed>250 && unitconsumed<=500) && ctype=="Domestic") {
            	amount=(int)((unitconsumed*6.30)+50);
            }
            else if((unitconsumed>500 && unitconsumed<=800) && ctype=="Domestic") {
            	amount=(int)((unitconsumed*7.10)+50);
            }
            else if(unitconsumed>800 && ctype=="Domestic") {
            	amount=(int)((unitconsumed*7.10)+50);
            }
            else if(ctype=="Commerial(LT)") {
            	amount=(int)((unitconsumed*7.52)+110);
            }
            else if(ctype=="Industrial(HT)") {
            	amount=(int)((unitconsumed*8.40)+330);
            }
            bill.readBill(custid, unitconsumed, amount);
			String query="{CALL Insertbill(?, ?, ?, ?, ?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, bill.getCustomerID());
            ct.setString(2, bill.getBillDate());
            ct.setInt(3, bill.getUnit());
            ct.setInt(4, bill.getBillAmt());
            ct.setBoolean(5, bill.getPaymentStatus());
            ct.executeQuery();
			return true;
		}
		catch(Exception e) {
			System.out.println("Error adding book to database");
            e.printStackTrace();
            return false;
		}
	}
	
	public void getBills(int tid) {
		try{
			String query = "{CALL Displaybills(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ResultSet rs = ct.executeQuery();
            System.out.println("---------------------------------------------------");
            while(rs.next()) {
            	System.out.println("Bill ID :: "+rs.getInt("billid"));
            	System.out.println("Customer ID :: "+rs.getInt("customerid"));
            	System.out.println("Bill date :: "+rs.getString("date"));
            	System.out.println("Unit consumed :: "+rs.getInt("unit"));
            	System.out.println("Bill amount :: Rs."+rs.getInt("amount")+"/-");
            	System.out.println("Payment status :: "+rs.getBoolean("status"));
            	System.out.println("---------------------------------------------------");
            }
		}
		catch(SQLException sqe) {
			System.out.println("Error from database");
            sqe.printStackTrace();
		}
	}
	
	public void getLatestBill(int tid) {
		try{
			String query = "{CALL Onebill(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ResultSet rs = ct.executeQuery();
            System.out.println("---------------------------------------------------");
            if(rs.next()) {
            	System.out.println("Bill ID :: "+rs.getInt("billid"));
            	System.out.println("Customer ID :: "+rs.getInt("customerid"));
            	System.out.println("Bill date :: "+rs.getString("date"));
            	System.out.println("Unit consumed :: "+rs.getInt("unit"));
            	System.out.println("Bill amount :: Rs."+rs.getInt("amount")+"/-");
            	System.out.println("Payment status :: "+rs.getBoolean("status"));
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
	public void UpdateBill(int tid) {
		try{
			
			String query1 = "{? = CALL Updatedamount(?)}";
            CallableStatement ct1 = connection.prepareCall(query1);
            ct1.setInt(2, tid);
            ct1.registerOutParameter(1, Types.INTEGER);
            ct1.execute();
            int upamt=ct1.getInt(1);
			
			String query = "{CALL Updatebill(?, ?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ct.setInt(2, upamt);
            ct.executeQuery();
		}
		catch(SQLException sqe) {
			System.out.println("Error from database");
            sqe.printStackTrace();
		}
	}
	
	public void payBill(int cid) {
		try {
			String query="{CALL Paybill(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, cid);
            ct.executeQuery();
            System.out.println("Bill paid successfully...");
		} catch (Exception e) {
			System.out.println("Unsucessful payment....!\nTry again after sometime.");
            e.printStackTrace();
		}
	}
}
