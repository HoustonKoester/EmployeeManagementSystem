package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.EmployeeServiceImpl;

class ImplTest {
	private EmployeeServiceImpl esi;
	@BeforeEach
	void initEach() {
		esi = new EmployeeServiceImpl();
	}
	
	
	@Test
	void yearlySalary() {
		assertEquals(14400, esi.calculateYearlySalary(1));
				
	}
	
	@Test
	void findEmp() {
		assertEquals(EmployeeServiceImpl.employ.get(0), esi.findByEmployeeNo(1));
				
	}
}