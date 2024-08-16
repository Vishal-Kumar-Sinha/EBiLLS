package com.ebills.core;

import java.sql.Connection;
import java.util.Scanner;

import com.ebills.util.CustomerCURD;
import com.ebills.models.Customer;
import com.ebills.util.BillCURD;

public class CustomerOps {
	public static void CustomersOps(CustomerCURD ccurd, Connection connection, int chk) {
		Scanner sc=new Scanner(System.in);
		BillCURD bcurd=new BillCURD(connection);
		Customer customer=new Customer();
		//while(true) {
			System.out.println("________________________________________________");
	        System.out.println("\t+------------------------------+");
	        System.out.println("\t|  Welcome to EBillS-Customer  |");
	        System.out.println("\t+------------------------------+");
	        System.out.println("________________________________________________");
	        System.out.print("Customer operations :: \n1. View your details\n2. Update your details\n3. View latest bill\n4. View all bills\n5. Pay bill\n6. Logout\nEnter choice ::");
	        int ch=sc.nextInt();
	        if(ch==1) {
	        		ccurd.getOneCustomer(chk);
	        }
	        else if(ch==2) {
	        		ccurd.updateCustomer(customer, chk);
	        }
	        else if(ch==3) {
	        		bcurd.getLatestBill(chk);
	        }
	        else if(ch==4) {
	        		bcurd.getBills(chk);
	        }
	        else if(ch==5) {
	        		bcurd.payBill(chk);
	        }
	        else if(ch==6) {
	        		sc.close();
	        		ccurd=null;
	        		bcurd=null;
	        		customer=null;
	        		System.gc();
	        		return;
	        }
	        else {
	        		System.out.println("Invalid choice");
	        }
        //}
	        sc.close();
	}
}
