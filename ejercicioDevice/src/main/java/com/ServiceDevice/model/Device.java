package com.ServiceDevice.model;

import java.util.Date;

public class Device {
	
 public String getMac() {
		return Mac;
	}
	public void setMac(String mac) {
		Mac = mac;
	}

 public String getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}

String Mac;
 String Timestamp;
 
}
