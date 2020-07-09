package com.codetest.cityconns.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codetest.cityconns.AvailableConnections;

import junit.framework.TestCase;

public class ConnectionsTest extends TestCase{

		List<String> routeLines = new ArrayList<String>();
		AvailableConnections availableConnections = new AvailableConnections();

		@Override
	    protected void setUp() throws Exception{
	        super.setUp();
	    }

	    @Test
	    public void testExistingConnections(){
	        assertTrue("Yes".equalsIgnoreCase(availableConnections.connectionsResponse("Boston", "Newark")));
	    }
	    
	    @Test
	    public void testNullCondition(){
	        assertTrue("No".equalsIgnoreCase(availableConnections.connectionsResponse(null, null)));
	    } 
	    
	    @Test
	    public void testEmptyCondition(){
	        assertTrue("No".equalsIgnoreCase(availableConnections.connectionsResponse("", "")));
	    } 
	    @Test
	    public void testNonExistingConnectionsyCondition(){
	        assertTrue("No".equalsIgnoreCase(availableConnections.connectionsResponse("Tokyo", "Beijing")));
	    } 
	    

	    public AvailableConnections getAvailableConnections() {
			return availableConnections;
		}

		public void setAvailableConnections(AvailableConnections availableConnections) {
			this.availableConnections = availableConnections;
		}
}
