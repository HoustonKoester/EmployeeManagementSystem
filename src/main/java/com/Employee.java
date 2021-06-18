package com;

public class Employee {
private int empNo;
private String empName;
private double Salary;
private Address address;
public Employee(int empNo, String empName, double salary, Address address) {
	super();
	this.empNo = empNo;
	this.empName = empName;
	Salary = salary;
	this.address = address;
}
public Employee() {
	// TODO Auto-generated constructor stub
}
public int getEmpNo() {
	return empNo;
}
public void setEmpNo(int empNo) {
	this.empNo = empNo;
}
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public double getSalary() {
	return Salary;
}
public void setSalary(double salary) {
	Salary = salary;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
@Override
public String toString() {
	return "ID: " + empNo + ", Name: " + empName + ", Monthly Salary: " + Salary+ ", "+ address.toString();
}


}
