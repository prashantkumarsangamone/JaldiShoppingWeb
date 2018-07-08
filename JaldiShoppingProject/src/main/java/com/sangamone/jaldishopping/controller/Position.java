package com.sangamone.jaldishopping.controller;

public class Position {
	  private String x;
	    private String y;
	    private String orientation;

	    public Position(String x, String y, String orientation) {
	        this.x = x;
	        this.y = y;
	        this.orientation = orientation;
	    }

		public String getX() {
			return x;
		}

		public void setX(String x) {
			this.x = x;
		}

		public String getY() {
			return y;
		}

		public void setY(String y) {
			this.y = y;
		}

		public String getOrientation() {
			return orientation;
		}

		public void setOrientation(String orientation) {
			this.orientation = orientation;
		}
	    
}
