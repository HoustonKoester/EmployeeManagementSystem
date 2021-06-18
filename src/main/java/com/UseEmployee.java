package com;


import java.util.logging.Logger;

import com.EmployeeServiceImpl.EmployeeNotFound;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;

public class UseEmployee {
	private static final Logger LOGGER = Logger.getLogger(UseEmployee.class.getName());
	
	public static void main(String[] args) throws EmployeeNotFound {
		// TODO Auto-generated method stub
		EmployeeServiceImpl esi = new EmployeeServiceImpl();
		esi.init();
		System.out.println("Welcome to the employee management system\n");
		Scanner s = new Scanner(System.in);
		int sw = 0; 
		while(true) {
			System.out.println("Enter a number from the menu selection below\n"
					+ "1. Display all employees\n"
					+ "2. Add a new employee\n"
					+ "3. Update employee information\n"
					+ "4. Find an employee using registered information\n"
					+ "5. Calculate an employee's yearly salary\n"
					+ "6. Delete an employee from the system\n"
					+ "7. Quit the employee management system");
			
			try {
			sw = s.nextInt();
			}catch(InputMismatchException e) {
				s.nextLine();
			}
			
			switch(sw) {
			case(1):
				esi.displayAllEmployees();
				System.out.println();
				break;
				
			case(2):
				esi.addEmployee();
				LOGGER.log(Level.INFO, "New Employee created");
				break;
				
			case(3):
				esi.updateEmployee();
			LOGGER.log(Level.INFO, "Employee updated");
				break;
				
			case(4):
				System.out.println("Enter the employee ID Number you wish to find:");
				int id = s.nextInt();
				Employee ret = esi.findByEmployeeNo(id);
				if(ret == null) {
					System.out.println("Sorry there was no employee with the ID Number: " + id);
				}else {
					System.out.println("The employee with ID number: " + id + " is : " + esi.findByEmployeeNo(id).toString());
				}
				
				break;
				
			case(5):
				System.out.println("Enter the employee ID for the employee that you're calculating their yearly salary for:");
				id = s.nextInt();
				double salary = esi.calculateYearlySalary(id);
				if(salary == -1) {
					System.out.println("Sorry there was no employee with the ID Number: " + id);
				}else {
					System.out.println("The salary for Employee: " + esi.findByEmployeeNo(id).getEmpName() + " is: " + salary +"$ per year");
				}
				break;
			
			case(6):
				esi.deleteEmployee();
				break;
				
			case(7):
				s.close();
				System.out.println("Thanks for using the employee management system\nGoodbye.");
				System.exit(0);
			default:
				System.out.println("Command not recognized, enter a command from the menu below\n");
				LOGGER.log(Level.WARNING, "Improper command selected");
				
			}

		}
		
	}
	
}
