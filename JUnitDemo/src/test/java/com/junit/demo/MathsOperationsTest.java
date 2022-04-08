package com.junit.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)//if this is added then beforeAllInit() will work without static
class MathsOperationsTest {

	MathsOperations mathsOp; 
	
	@BeforeAll//if this method is not mentioned as static then it will give error(applicable for @BeforeAll() and @AfterAll())
	void beforeAllInit() {
		System.out.println("Before all intit()...");
	}
	
	
	//addd comment
	@BeforeEach // init() :- any name is allowed for this method
	void init() {
		mathsOp = new MathsOperations();
	}
	
	
	@AfterEach
	void cleanup() {
		System.out.println("cleaning up..");
	}
	
	
	@Test
	@Tag("Maths")//used to classify the tests on the basis of tag.i.e if Maths tag is selected to run(go in run configuration and create new config. then include the tag) then only Maths tag test cases will get executed..
	@DisplayName("Testing Add() method") // to add or chnge the name of test in JUnit window 
	void testAdd() {
		 System.out.println("This test ran..!"); // for successfull test case
		//fail("Not yet implemented"); // for fail cases
		 
		// MathsOperations mathsOp = new MathsOperations();
		 
		 int expected = 4; // pass case
		 // int expected = 5 is failed case
		 
		 int actual = mathsOp.add(2, 2);//2+2=4 i.e expected value
		 
		 assertEquals(expected, actual, "add() should add the numbers..");
		 // the msg will be shown when the test case is failed..
		 
	}
	
	
	
	
	@Test
	@Tag("Circle")//used to classify the tests on the basis of tag.
	//@EnabledOnOs(OS.LINUX) used as disabled()
	@DisplayName("Testing AreaOfCircle()")
	void testAreaOfCircle() {
		
		boolean isServerOn = false; // if true then this test will get executed
		assumeTrue(isServerOn); // if it is true then only this test will run and if it is false then test will not run
		
		
		//MathsOperations mathsOp = new MathsOperations();
		assertEquals(314.1592653589793, mathsOp.areaOfCircle(10), "Should return area of circle..");
		
	}
	
	
	
	@Test
	@Tag("Maths")
	@DisplayName("Testing Divide()")
	void testDivide() {
	
		
		//MathsOperations mathsOp = new MathsOperations();
		assertThrows(ArithmeticException.class, () -> mathsOp.divide(1,0), "/ by 0 should throw");//true if divide by 0 is thrown
		//if not thrown then fail case.
	}
	
	
	
	
	@Test
	@Tag("Maths")
	//@RepeatedTest(value = 2)//instated @Test we can use @RepaestedTest .. used to repeat the test case and we should specify the value i.e how many times we want to repeate that test.
	@DisplayName("Testing Multi()")
	void testMulti() {
		
		//assertEquals(6, mathsOp.multi(3, 2), "should return the multiplicatn of no.");
		//assertAll() is introduced in JUnit 5
		//if we want to test bunch of assert statement in single shot then we should use assertAll() insteadt of assertEquals()
		assertAll(
				() -> assertEquals(0, mathsOp.multi(3, 0)),
				
				() -> assertEquals(-4, mathsOp.multi(-2, 2))
			
				);
	}
	
	
	
	
	
	@Test
	@Disabled  // by this the test is disabled (i.e it is not executed/run)
	@DisplayName("testing disabled")
	void testDisabled() {
		
		fail("Testing disabled()..");
	}
	
	
	
	// nested class eg:-(for single add() operation multiple test cases is written in single class)
	//if any test failes then whole test result shows fail.
	@Nested
	@DisplayName("Add Cases in mathsOperation")
	class AddTest {
		
		
		@Test
		@DisplayName("Positive add case")
		void testAddPositive() {
			
			assertEquals(4, mathsOp.add(2, 2), "Addition of positive no.");
			
		}
		
		@Test
		@DisplayName("Negative add case")
		void testAddNegative() {
			
			int expected = -2;
			int actual   = mathsOp.add(-1, -1);
			
			//passing lambda for msg i.e it means if the test cases failes then the msg is goin to disply and it will get loaded when the test cases is failed.
			//assertEquals(expected, actual, ()-> "should return Addition of negative no." +expected+" but returned  "+actual);
			
			
			assertEquals(-2, mathsOp.add(-1, -1), "Addition of negative no.");
			//assertEquals(-3, mathsOp.add(-1, -1), "Addition of negative no.");this fails so whole case results fails
			
		}
		
		
	}
}
