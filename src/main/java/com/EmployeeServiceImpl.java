package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	}
};
	public List<Employee>  init(){
		String QUERY = "select * from emp order by ID asc";
	    employ = new ArrayList<>();
	    try (Connection con = ConnectionUtil.getConnection();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(QUERY)) {
	      while (rs.next()) {
	        Employee emp = new Employee();
	        emp.setEmpNo(rs.getInt("ID"));
	        emp.setEmpName(rs.getString("empname"));
	        emp.setSalary(rs.getDouble("salary"));
	        Address addr = new Address();
	        addr.setCity(rs.getString("city"));
	        addr.setState(rs.getString("state"));
	        emp.setAddress(addr);
	        employ.add(emp);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return employ;

	}
	
	public List<Employee>  displayAllEmployees() {
		// TODO Auto-generated method stub
		String QUERY = "select * from emp order by ID asc";
	    employ = new ArrayList<>();
	    try (Connection con = ConnectionUtil.getConnection();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(QUERY)) {
	      while (rs.next()) {
	        Employee emp = new Employee();
	        emp.setEmpNo(rs.getInt("ID"));
	        emp.setEmpName(rs.getString("empname"));
	        emp.setSalary(rs.getDouble("salary"));
	        Address addr = new Address();
	        addr.setCity(rs.getString("city"));
	        addr.setState(rs.getString("state"));
	        emp.setAddress(addr);
	        employ.add(emp);
	        System.out.println(emp.toString());
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return employ;
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
				int sw = -1;
				label:
				while(true) {
					System.out.println("1: update the employee Name\n2: update the employee Monthly Salary\n3: update the employee Address\n4: Back to Main Menu\n");
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
					 String QUERY = "UPDATE emp SET empName=? WHERE ID=?";
					    try (Connection conn = ConnectionUtil.getConnection();
					        PreparedStatement stmt1 = conn.prepareStatement(QUERY)) {
					      stmt1.setString(1, emp.getEmpName());
					      stmt1.setInt(2, emp.getEmpNo());
					      stmt1.executeUpdate();
					      } catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					break;
				
				case(2):
					System.out.println("You're currently updating " + emp.getEmpName() + "'s Salary\nIt is currently set to: "+ emp.getSalary());
					System.out.println("Enter the new Monthly Salary you wish to update it to:\n");
					double salary = s.nextDouble();
					emp.setSalary(salary);
					System.out.println("The employee's Monthly Salary is now set to: " + emp.getSalary());
					 String QUERY2 = "UPDATE emp SET salary=? WHERE ID=?";
					    try (Connection conn = ConnectionUtil.getConnection();
					        PreparedStatement stmt1 = conn.prepareStatement(QUERY2)) {
					      stmt1.setDouble(1, emp.getSalary());
					      stmt1.setInt(2, emp.getEmpNo());
					      stmt1.executeUpdate();
					      } catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
					 String QUERY3 = "UPDATE emp SET city=?, state=? WHERE ID=?";
					    try (Connection conn = ConnectionUtil.getConnection();
					        PreparedStatement stmt1 = conn.prepareStatement(QUERY3)) {
					      stmt1.setString(1, emp.getAddress().getCity());
					      stmt1.setString(2, emp.getAddress().getState());
					      stmt1.setInt(3, emp.getEmpNo());
					      stmt1.executeUpdate();
					      } catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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

	public void deleteEmployee() throws EmployeeNotFound {
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
					String QUERY = "Delete from emp where ID = ?";
				    try (Connection conn = ConnectionUtil.getConnection(); 
				    	PreparedStatement stmt = conn.prepareStatement(QUERY)) {
				      stmt.setInt(1, id);
				      stmt.executeUpdate();
				    } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					employ.remove(emp);
					LOGGER.log(Level.WARNING, "Employee deleted, ensure authenticity.");
					break;
				}else if(confirm.toLowerCase().equals("no")) {
					LOGGER.log(Level.INFO, "No Employees Deleted.");
					System.out.println("Returning you to main menu");
				}else {
					System.out.println("Command not recognized returning to main menu");
				}
				//s.close();
				
			}
		}
		if(flag == 1) {
	
			LOGGER.log(Level.WARNING, "No employee found");
			throw new EmployeeNotFound();
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
		Employee emp = new Employee(newID, name, salary, new Address(city,state));
		employ.add(emp);
		System.out.println("New employee created: "+ employ.get(employ.size()-1).toString());
		
		String QUERY = "insert into emp values(?,?,?,?,?)";
		   try (Connection con = ConnectionUtil.getConnection();
				   PreparedStatement stmt = con.prepareStatement(QUERY)) {
			   
			   		stmt.setInt(1, emp.getEmpNo());
			   		stmt.setString(2, emp.getEmpName());
			   		stmt.setDouble(3, emp.getSalary());
			   		stmt.setString(4, emp.getAddress().getCity());
			   		stmt.setString(5, emp.getAddress().getState());
			   		stmt.executeUpdate();
			    } catch (SQLException e) {
			      e.printStackTrace();
			    }
		   
		   System.out.println("Employee" + emp.toString() + " was added to the system.");
		   
	}

	public class EmployeeNotFound extends Exception {
		private static final long serialVersionUID = 1L;
		 public EmployeeNotFound() {
			 return;
		  }
		  }
	
}

