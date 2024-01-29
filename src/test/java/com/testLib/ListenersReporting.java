package com.testLib;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.ExtentManager;


public  class ListenersReporting implements ITestListener {
	// ExtentReports extent;
	 private ExtentReports extent = ExtentManager.getInstance();
 ExtentTest test;
	


	public void onStart(ITestContext context) {
		System.out.println("Started execution for : " + context.getName());
		System.out.println("---------------------------------");
	}

	public void onTestStart(ITestResult result) {
		System.out.println("execution for test started : " + result.getName());
		test = extent.createTest(result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed : " + result.getName());
		test.log(Status.PASS, "TEST HAS PASSED");
		System.out.println("---------------------------------");

	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed : " + result.getName());
		test.log(Status.FAIL, "TEST HAS FAILED");
		System.out.println("---------------------------------");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped : " + result.getName());
		test.log(Status.SKIP, "TEST HAS SKIPPED");
		System.out.println("---------------------------------");
	}

	public void onFinish(ITestContext context) {
		System.out.println("execution done for : " + context.getName());
		System.out.println("********************************");
		extent.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("onTestFailedButWithinSuccessPercentage : " + result.getName());
		
	}
}