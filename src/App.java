import com.ebills.core.*;
import com.ebills.util.*;

import java.sql.Connection;
import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        try {
        	
	        	Scanner s=new Scanner(System.in);
		        System.out.println("________________________________________________");
		        System.out.println("\t+---------------------+");
		        System.out.println("\t|  Welcome to EBillS  |");
		        System.out.println("\t+---------------------+");
		        System.out.println("________________________________________________");
		        System.out.println("\nPlease Login to continue...\n\n\n");
		        System.out.print("1. Login as Admin\n2. Login as Employee\n3. Login as Customer\n4. Exit\nEnter your choice :: ");
		        int ch1=s.nextInt();
		        if(ch1==1) {
		        	try {
						AdminCURD acurd=new AdminCURD(connection);
						int chk=acurd.loginAdmin();
						if(chk==0) {
							acurd=null;
							System.gc();
						}
						else {
							AdminOps.AdminsOps(acurd, connection);
						}
					} catch (Exception e) {
						System.out.println("Exception occurred "+e.getMessage());
					}
		        }
		        else if(ch1==2) {
		        	try {
						EmployeeCURD ecurd=new EmployeeCURD(connection);
						int chk=ecurd.loginEmployee();
						if(chk==0) {
							ecurd=null;
							System.gc();
						}
						else {					
					        EmployeeOps.EmployeesOps(ecurd, connection, chk);
						}
					} catch (Exception e) {
						System.out.println("Exception occurred "+e.getMessage());
					}
		        }
		        else if(ch1==3) {
		        	try {
						CustomerCURD ccurd=new CustomerCURD(connection);
						int chk=ccurd.loginCustomer();
						if(chk==0) {
							ccurd=null;
							System.gc();
						}
						else {
							CustomerOps.CustomersOps(ccurd, connection, chk);
						}
					} catch (Exception e) {
						System.out.println("Exception occurred "+e.getMessage());
					}
		        }
		        else if(ch1==4) {
		        	s.close();
		            System.out.println("________________________________________________");
		            databaseConnection.closeConnection();
		            System.out.println("________________________________________________");
		        	System.exit(0);
		        }
		        else {
		        	System.out.println("Invalid choice");
		        }
        } catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
