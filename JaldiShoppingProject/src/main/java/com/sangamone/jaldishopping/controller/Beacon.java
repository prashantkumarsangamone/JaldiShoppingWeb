package com.sangamone.jaldishopping.controller;

public class Beacon {

	 private String mac;
	    private String color;
	    private String uuid;
	    private String major;
	    private String minor;

	    public Beacon(String mac, String color, String uuid, String major, String minor) {
	        this.mac = mac;
	        this.color = color;
	        this.uuid = uuid;
	        this.major = major;
	        this.minor = minor;
	    }

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}
}

