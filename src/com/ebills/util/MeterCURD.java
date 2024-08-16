package com.ebills.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ebills.models.Meter;

public class MeterCURD {
private Connection connection=null;
	
	public MeterCURD(Connection connection) {
		this.connection=connection;
	}
	
	public int NewMeter(Meter meter, int eid) {
		try {
			meter.readMeter(eid);
			String query="{CALL Insertmeter(?, ?, ?, ?, ?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, meter.getCustomerid());
            ct.setInt(2, meter.getReading());
            ct.setString(3, meter.getRdate());
            ct.setInt(4, meter.getEmployeeid());
            ct.setBoolean(5, meter.getStatus());
            ct.executeQuery();
			return meter.getCustomerid();
		}
		catch(Exception e) {
			System.out.println("Error adding book to database");
            e.printStackTrace();
            return 0;
		}
	}
	
	public void getOneMeter(int tid) {
		try{
			String query = "{CALL Onemeter(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ResultSet rs = ct.executeQuery();
            if(rs.next()) {
            	System.out.println("---------------------------------------------------");
            	System.out.println("Meter ID :: "+rs.getInt("id"));
            	System.out.println("Meter customerid :: "+rs.getInt("customerid"));
            	System.out.println("Meter reading :: "+rs.getInt("reading"));
            	System.out.println("Meter reading date :: "+rs.getString("rdate"));
            	System.out.println("Meter employeeid :: "+rs.getInt("employeeid"));
            	System.out.println("Meter status :: "+rs.getBoolean("status"));
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
	
	public void getAllMeter(int tid) {
		try{
			String query = "{CALL Displaymeter(?)}";
            CallableStatement ct = connection.prepareCall(query);
            ct.setInt(1, tid);
            ResultSet rs = ct.executeQuery();
            System.out.println("---------------------------------------------------");
            while(rs.next()) {
            	System.out.println("Meter ID :: "+rs.getInt("id"));
            	System.out.println("Meter customerid :: "+rs.getInt("customerid"));
            	System.out.println("Meter reading :: "+rs.getInt("reading"));
            	System.out.println("Meter reading date :: "+rs.getString("rdate"));
            	System.out.println("Meter employeeid :: "+rs.getInt("employeeid"));
            	System.out.println("Meter status :: "+rs.getBoolean("status"));
            	System.out.println("---------------------------------------------------");
            }
		}
		catch(SQLException sqe) {
			System.out.println("Error from database");
            sqe.printStackTrace();
		}
	}
	
}
