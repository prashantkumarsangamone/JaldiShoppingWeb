package com.sangamone.jaldishopping.utils;


import com.fasterxml.jackson.core.JsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sangamone.jaldishopping.controller.Beacon;
import com.sangamone.jaldishopping.controller.Beacons;
import com.sangamone.jaldishopping.controller.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class BeaconsDeserializer  extends JsonDeserializer<List<Beacons>> {

    @Override
    public List<Beacons> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        ArrayNode arrayNode = oc.readTree(jsonParser);

        List<Beacons> beaconsList = Collections.EMPTY_LIST;
        if(arrayNode.size()>0)
            beaconsList = new ArrayList<Beacons>();
        for(JsonNode node :arrayNode){
            JsonNode beaconNode = node.get("beacon");
            String mac = beaconNode.get("mac").asText();
            String color = beaconNode.get("color").asText();
            String uuid = beaconNode.get("uuid").asText();
            String major = beaconNode.get("major").asText();
            String minor = beaconNode.get("minor").asText();

            JsonNode positionNode = node.get("position");
            String x = positionNode.get("x").asText();
            String y = positionNode.get("y").asText();
            String orientation = positionNode.get("orientation").asText();

            beaconsList.add(new Beacons(
                    new Beacon(mac, color, uuid, major, minor),
                    new Position(x,y, orientation)));
        }
        return beaconsList;
    }

}

/*class Beacons{
    private Beacon beacon;
    private Position position;

    public Beacons(Beacon beacon, Position position) {
        this.beacon = beacon;
        this.position = position;
    }
}

class Beacon{
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
}

class Position{
    private String x;
    private String y;
    private String orientation;

    public Position(String x, String y, String orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }
}*/

