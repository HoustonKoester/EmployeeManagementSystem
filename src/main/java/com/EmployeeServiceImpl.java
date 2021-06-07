package com;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Level;
import java.util.logging.Logger;
public class EmployeeServiceImpl implements EmployeeService{
	private static final Logger LOGGER = Logger.getLogger(UseEmployee.class.getName());
static List<Employee> employ = new ArrayList<Employee>() {
//default serial ID
	private static final long serialVersionUID = 1L;
	{
		//Your two starting employees
	add(new Employee(1, "Gary", 1200, new Address("Prescott Valley", "Arizona")));
	add(new Employee(2, "John", 1400, new Address("Prescott", "Arizona")));
	}
};
	
	
	public void displayAllEmployees() {
		// TODO Auto-generated method stub
		for(Employee emp : employ) {
			System.out.println(emp.toString());
		}
	}
		//unused method, never passing employee directly
	public double calculateYearlySalary(Employee e1) {
		// TODO Auto-generated method stub
		double Yearly = e1.getSalary()*12;
		return Yearly;
	}
	
	//method uses employee id, future proofing for sql primary key
	public double calculateYearlySalary(int id) {
		// TODO Auto-generated method stub
		double Yearly = -1;
		for(Employee emp : employ) {
			if(emp.getEmpNo() == id) {
				Yearly = emp.getSalary()*12;
			}
		}
		return Yearly;
	}
	//method uses employee id, future proofing for sql primary key
	public Employee findByEmployeeNo(int empNo) {
		return employ.stream().filter(e -> e.getEmpNo() == empNo).findFirst().orElse(null);
	}

	//void type, with no direct passed data, but asks for information to update employee
	//used to make feel more like broken down menus that you would expect a employee registery to have
	public void updateEmployee() {
		// TODO Auto-generated method stub
		System.out.println("Enter the employee ID of the employee that you wish to update:\n");
		Scanner s = new Scanner(System.in);
		int id = s.nextInt();
		int flag = 1;
		for(Employee emp : employ) {
			if(emp.getEmpNo() == id) {
				flag = 0;
				System.out.println("1: update the employee Name\n2: update the employee Monthly Salary\n3: update the employee Address\n4: Back to Main Menu\n");
				int sw = -1;
				label:
				while(true) {
					try {
						sw = s.nextInt();
						}catch(InputMismatchException e) {
							s.nextLine();
						}
				//menu for updating employee information
				switch(sw) {
				case(1):
					System.out.println("You're currently updating " + emp.getEmpName() + "'s name\n");
					System.out.println("Enter the new name you wish to update it to:\n");
					s.nextLine();
					String name = s.nextLine();
					emp.setEmpName(name);
					System.out.println("The employee's name is now set to: " + emp.getEmpName());
					break;
				
				case(2):
					System.out.println("You're currently updating " + emp.getEmpName() + "'s Salary\nIt is currently set to: "+ emp.getSalary());
					System.out.println("Enter the new Monthly Salary you wish to update it to:\n");
					double salary = s.nextDouble();
					emp.setSalary(salary);
					System.out.println("The employee's Monthly Salary is now set to: " + emp.getSalary());
					break;
					
				case(3):
					System.out.println("You're currently updating " + emp.getEmpName() + "'s Address\nIt is currently set to: "+ emp.getAddress().getCity() +", " + emp.getAddress().getState());
					System.out.println("Enter the updated City you wish to update it to:\n");
					s.nextLine();
					String city = s.nextLine();
					System.out.println("Enter the updated State you wish to update it to:\n");
					String state = s.nextLine();
					emp.setAddress(new Address(city,state));
					System.out.println("The employee's Address is now set to: " + emp.getAddress().getCity() +", " + emp.getAddress().getState());
					break;
				case(4):
					break label;
				default:
					System.out.println("Command not recognized, please enter 1-4");
					
					}
				}
			}
			
		}
		if(flag == 1) { // flag for when no user is found
			System.out.println("There was no user with the id: " + id);
			LOGGER.log(Level.WARNING, "No employee found");
		}
		//s.close();
	}

	public void deleteEmployee() {
		// TODO Auto-generated method stub
		System.out.println("Enter the employee ID of the employee that you wish to delete:\n");
		Scanner s = new Scanner(System.in);
		int id = s.nextInt();
		int flag = 1;
		for(Employee emp : employ) {
			if(emp.getEmpNo() == id) {
				flag=0;
				System.out.println("Are you sure you want to delete employee:\n" + emp.toString() + "\nEnter Yes or No:");
				s.nextLine();
				String confirm = s.nextLine();
				System.out.println(confirm);
				if(confirm.toLowerCase().equals("yes")) {
					employ.remove(emp);
					break;
				}else if(confirm.toLowerCase().equals("no")) {
					System.out.println("Returning you to main menu");
				}else {
					System.out.println("Command not recognized returning to main menu");
				}
				//s.close();
				
			}
		}
		if(flag ==1) {
			System.out.println("There was no user with the id: " + id);
			LOGGER.log(Level.WARNING, "No employee found");
		}
	}

	public void addEmployee() {
		// TODO Auto-generated method stub
		int newID = employ.get(employ.size()-1).getEmpNo() + 1;
		System.out.println("New ID was autogenerated for the Employee with the value of: " + newID +"\n");
		
		System.out.println("Enter a name for the new Employee: ");
		Scanner s = new Scanner(System.in);
		String name = s.nextLine();
		System.out.println("Enter a Monthly Salary for the new Employee: ");
		double salary = s.nextDouble();
		System.out.println("Enter the Employee's City: ");
		s.nextLine();
		String city = s.nextLine();
		System.out.println("Enter the Employee's State: ");
		String state = s.nextLine();
		employ.add(new Employee(newID, name, salary, new Address(city,state)));
		System.out.println("New employee created: "+ employ.get(employ.size()-1).toString());
	}

}

