package com.ebills.models;


public class Bill {
	private int billid=0;
	private int customerid=0;
	private String date="";
	private int unit=0;
	private int amt=0;
	private boolean status=false;
	
	public int getBillID() {
		return billid;
	}
	public int getCustomerID() {
		return customerid;
	}
	public String getBillDate() {
		return date;
	}
	public int getBillAmt() {
		return amt;
	}
	public boolean getPaymentStatus() {
		return status;
	}
	public int getUnit() {
		return unit;
	}
	
	public void readBill(int custid, int unitconsumed, int amount) {
		try {
			customerid=custid;
			date=java.time.LocalDate.now().toString();
			unit=unitconsumed;
			amt=amount;
		}
		catch(Exception e) {
			System.out.println("Exception :: "+e.getMessage());
		}
	}
	
	public String toString() {
		return "Bill{" +
				"billid=" + billid +
                "customerid=" + customerid +
                ", date='" + date + '\'' +
                ", unit=" + unit + 
                ", amt='" + amt +
                ", status='" + status + '\'' +
                '}';
	}
}
