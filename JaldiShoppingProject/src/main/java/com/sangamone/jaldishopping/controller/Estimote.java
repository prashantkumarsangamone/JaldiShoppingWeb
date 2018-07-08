package com.sangamone.jaldishopping.controller;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sangamone.jaldishopping.utils.BeaconsDeserializer;

	public class Estimote {
		
		
		@JsonIgnoreProperties(value = {})
		//public List<Estimote> estimote;

		public String identifier;

	    private List<Map<String, String>> walls;

	    private List<String> linear_objects;
	    
	    @JsonDeserialize(using = BeaconsDeserializer.class)
	  		private List<Beacons> beacons;

		public List<String> stickers;

		public String owner;

		public int location_id;

	    private Location location;

		public String latitude;

		public String longitude;

		public String draft;

		public String accepted;

		public Map<String, List<String>> meta;

		public  List<String> pins;

		@JsonProperty("public")
		public String isPublic;

		public String getIdentifier() {
			return identifier;
		}

		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}

		public List<Map<String, String>> getWalls() {
			return walls;
		}

		public void setWalls(List<Map<String, String>> walls) {
			this.walls = walls;
		}

		public List<String> getLinear_objects() {
			return linear_objects;
		}

		public void setLinear_objects(List<String> linear_objects) {
			this.linear_objects = linear_objects;
		}

		public List<Beacons> getBeacons() {
			return beacons;
		}

		public void setBeacons(List<Beacons> beacons) {
			this.beacons = beacons;
		}

		public List<String> getStickers() {
			return stickers;
		}

		public void setStickers(List<String> stickers) {
			this.stickers = stickers;
		}

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public int getLocation_id() {
			return location_id;
		}

		public void setLocation_id(int location_id) {
			this.location_id = location_id;
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getDraft() {
			return draft;
		}

		public void setDraft(String draft) {
			this.draft = draft;
		}

		public String getAccepted() {
			return accepted;
		}

		public void setAccepted(String accepted) {
			this.accepted = accepted;
		}

		public Map<String, List<String>> getMeta() {
			return meta;
		}

		public void setMeta(Map<String, List<String>> meta) {
			this.meta = meta;
		}

		public List<String> getPins() {
			return pins;
		}

		public void setPins(List<String> pins) {
			this.pins = pins;
		}

		public String getIsPublic() {
			return isPublic;
		}

		public void setIsPublic(String isPublic) {
			this.isPublic = isPublic;
		}

		
	
		
}