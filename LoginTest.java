package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.executionengine.KeyWordEngine;

public class LoginTest {
	
	public KeyWordEngine keyWordEngine;
	@Test
	public void loginTest() {
		
		keyWordEngine = new KeyWordEngine();
		keyWordEngine.startExecution("loginsheet");
	}

}
