package com.x.ws.impl;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.x.ws.generated.RequestFlight;
import com.x.ws.generated.ResponseFlight;

public class TestBookMyFlightServiceImpl {
	
	
	@Test
	public void TestForFlightToDestExist(){
		String noFlPresent = "No Flights to Destination Present";
		BookMyFlightServiceImpl bmfsi = new BookMyFlightServiceImpl();
		RequestFlight reqFl = new RequestFlight();
		ResponseFlight resFl = new ResponseFlight();
		reqFl.setFromLoc("h");
		reqFl.setDestLoc("g");
		resFl = bmfsi.bookFlight(reqFl);
		
		assertEquals("No Flights Available to airport g", noFlPresent.toString(), resFl.getErrorMsg().toString());
		
	}
	
	@Test
	public void TestDirectFlightExist(){
		String expected = "hb";
		BookMyFlightServiceImpl bmfsi = new BookMyFlightServiceImpl();
		RequestFlight reqFl = new RequestFlight();
		ResponseFlight resFl = new ResponseFlight();
		reqFl.setFromLoc("h");
		reqFl.setDestLoc("b");
		resFl = bmfsi.bookFlight(reqFl);
		
		assertEquals("Direct Flight Available between h and b", expected.toString(), resFl.getFlPathSug().toString());
	}
	
	@Test
	public void TestConnectingFlight(){
		String expected = "deebbaaf";
		BookMyFlightServiceImpl bmfsi = new BookMyFlightServiceImpl();
		RequestFlight reqFl = new RequestFlight();
		ResponseFlight resFl = new ResponseFlight();
		reqFl.setFromLoc("d");
		reqFl.setDestLoc("f");
		resFl = bmfsi.bookFlight(reqFl);
		
		assertEquals("Connecting flight between d and f", expected.toString(), resFl.getFlPathSug().toString());
		
	}

}
