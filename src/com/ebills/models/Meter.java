package com.ebills.models;

import java.util.Scanner;

public class Meter {
	private int id=0;
	private int customerid=0;
	private int reading=0;
	private String rdate="";
	private int employeeid=0;
	private boolean status=false;
	
	public int getMID() {
		return id;
	}
	public int getCustomerid() {
		return customerid;
	}
	public int getReading() {
		return reading;
	}
	public String getRdate() {
		return rdate;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public boolean getStatus() {
		return status;
	}
	
	public void readMeter(int eid) {
		try(Scanner s=new Scanner(System.in)) {
			System.out.print("Enter customer-ID :: ");
			customerid=s.nextInt();
			System.out.print("Enter meter reading :: ");
			reading=s.nextInt();
			rdate=java.time.LocalDate.now().toString();
			employeeid=eid;
		}
		catch(Exception e) {
			System.out.println("Exception :: "+e.getMessage());
		}
	}
	
	public String toString() {
		return "Admin{" +
                "id=" + id +
                ", customerid='" + customerid + '\'' +
                ", reading='" + reading + '\'' +
                ", rdate='" + rdate + '\'' +
                ", employeeid='" + employeeid + '\'' +
                ", status='" + status + '\'' +
                '}';
	}
}
