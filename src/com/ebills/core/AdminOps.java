package com.ebills.core;

import com.ebills.models.Admin;
import com.ebills.models.Customer;
import com.ebills.models.Employee;
import com.ebills.util.AdminCURD;
import com.ebills.util.CustomerCURD;
import com.ebills.util.EmployeeCURD;

import java.sql.Connection;
import java.util.Scanner;

public class AdminOps {
	private static void EmpMaintenance(EmployeeCURD ecurd, Employee employee) {
		Scanner s=new Scanner(System.in);
		System.out.print("Employee Maintenance :: \n1. Create employee\n2. Display all employee\n3. Display one employee\n4. Update Employee \n5. Delete employee\nEnter choice :: ");
    	int ich1=s.nextInt();
    	switch(ich1) {
        	case 1:
        		ecurd.NewEmployee(employee);
        		break;
        	case 2:
        		ecurd.getAllEmployee();
        		break;
        	case 3:
        		System.out.print("Enter employee-ID :: ");
        		int t=s.nextInt();
        		ecurd.getOneEmployee(t);
        		break;
        	case 4:
        		System.out.print("Enter employee-ID :: ");
        		int t2=s.nextInt();
        		ecurd.updateEmployee(employee, t2);
        		break;
        	case 5:
        		System.out.print("Enter employee-ID :: ");
        		int t1=s.nextInt();
        		ecurd.deleteEmployee(t1);
        		break;
        	default:
        		System.out.println("Invalid choice");
    	}
    	s.close();
	}
	
	private static void Custmaintenance(CustomerCURD ccurd, Customer customer) {
		Scanner s=new Scanner(System.in);
		System.out.print("Customer Maintenance :: \n1. Display all customer\n2. Display one customer\nEnter choice :: ");
    	int ich2=s.nextInt();
    	switch(ich2) {
        	case 1:
        		ccurd.getAllCustomer();
        		break;
        	case 2:
        		System.out.print("Enter customer-ID :: ");
        		int t=s.nextInt();
        		ccurd.getOneCustomer(t);
        		break;
        	default:
        		System.out.println("Invalid choice");
    	}
    	s.close();
	}
	
	private static void AdminMaintenance(AdminCURD acurd, Admin admin) {
		Scanner s=new Scanner(System.in);
		System.out.print("Admin Maintenance :: \n1. Create admin\n2. Display all admin\n3. Display one admin\n4. Update admin\n5. Delete admin\nEnter choice :: ");
    	int ich3=s.nextInt();
    	switch(ich3) {
        	case 1:
        		acurd.NewAdmin(admin);
        		break;
        	case 2:
        		acurd.getAllAdmin();
        		break;
        	case 3:
        		System.out.print("Enter admin-ID :: ");
        		int t=s.nextInt();
        		acurd.getOneAdmin(t);
        		break;
        	case 4:
        		acurd.updateAdmin(admin);
        		break;
        	case 5:
        		System.out.print("Enter admin-ID :: ");
        		int t1=s.nextInt();
        		acurd.deleteAdmin(t1);
        		break;
        	default:
        		System.out.println("Invalid choice");
    	}
    	s.close();
	}
	
	public static void AdminsOps(AdminCURD acurd, Connection connection) {
		Admin admin=new Admin();
		Customer customer=new Customer();
		CustomerCURD ccurd=new CustomerCURD(connection);
		Employee employee=new Employee();
		EmployeeCURD ecurd=new EmployeeCURD(connection);
    	Scanner s=new Scanner(System.in);
		
		
		System.out.println("________________________________________________");
        System.out.println("\t+---------------------------+");
        System.out.println("\t|  Welcome to EBillS-Admin  |");
        System.out.println("\t+---------------------------+");
        System.out.println("________________________________________________");
        System.out.print("Admin operations :: \n1. Employee Maintenance\n2. Customer maintenence\n3. Admin maintenence\n 4. Exit\nEnter choice ::");
        int ch=s.nextInt();
        s.close();
        switch(ch) {
	        case 1:
	        	EmpMaintenance(ecurd, employee);
	        	break;
	        case 2:
	        	Custmaintenance(ccurd, customer);
	        	break;
	        case 3:
	        	AdminMaintenance(acurd, admin);
	        	break;
	        case 4:
	        	acurd=null;
	        	admin=null;
	        	ccurd=null;
	        	customer=null;
	        	ecurd=null;
	        	employee=null;
	        	System.gc();
	        	return;
	        default:
	        	System.out.println("Invalid choice");
        }
	}
}
