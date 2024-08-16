package com.ebills.core;

import com.ebills.models.Customer;
import com.ebills.models.Employee;
import com.ebills.models.Meter;
import com.ebills.models.Bill;
import com.ebills.util.CustomerCURD;
import com.ebills.util.EmployeeCURD;
import com.ebills.util.MeterCURD;
import com.ebills.util.BillCURD;

import java.sql.Connection;
import java.util.Scanner;

public class EmployeeOps {

	public static void EmployeesOps(EmployeeCURD ecurd, Connection connection, int chk) {
		Employee employee=new Employee();
		Customer customer=new Customer();
		CustomerCURD ccurd=new CustomerCURD(connection);
		Meter meter=new Meter();
		MeterCURD mcurd=new MeterCURD(connection);
		Bill bill=new Bill();
		BillCURD bcurd=new BillCURD(connection);
		Scanner s=new Scanner(System.in);
		
		System.out.println("________________________________________________");
        System.out.println("\t+------------------------------+");
        System.out.println("\t|  Welcome to EBillS-Employee  |");
        System.out.println("\t+------------------------------+");
        System.out.println("________________________________________________");
        System.out.print("Employee operations :: \n1. View your details\n2. Update your details\n3. View Customer details\n4. New Connection\n5. Take reading\n6. Exit\nEnter choice ::");
        int ch=s.nextInt();
        switch(ch) {
        	case 1:
        		ecurd.getOneEmployee(chk);
        		break;
        	case 2:
        		ecurd.updateEmployee(employee, chk);
        		break;
        	case 3:
        		System.out.print("Enter customer-ID :: ");
        		int t=s.nextInt();
        		ccurd.getOneCustomer(t);
        		break;
        	case 4:
        		ccurd.NewCustomer(customer);
        		break;
        	case 5:
        		int cid=mcurd.NewMeter(meter, chk);
        		bcurd.generateBill(bill, cid);
        		bcurd.UpdateBill(cid);
        		break;
        	case 6:
        		s.close();
        		ecurd=null;
        		employee=null;
        		customer=null;
        		ccurd=null;
        		meter=null;
        		mcurd=null;
        		System.gc();
        		return;
        	default:
        		System.out.println("Invalid choice");
        }
        s.close();
	}
}
