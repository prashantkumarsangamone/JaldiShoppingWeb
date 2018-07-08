package com.sangamone.jaldishopping.controller;

public class Beacons {
	
	  private Beacon beacon;
	    private Position position;

	    public Beacons(Beacon beacon, Position position) {
	        this.beacon = beacon;
	        this.position = position;
	    }

	public Beacon getBeacon() {
		return beacon;
	}

	public void setBeacon(Beacon beacon) {
		this.beacon = beacon;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}

