package com.example.potatopaloozac.traveldomainproj.data.network.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BusInformation{

	@SerializedName("businformation")
	private List<BusinformationItem> businformation;

	public void setBusinformation(List<BusinformationItem> businformation){
		this.businformation = businformation;
	}

	public List<BusinformationItem> getBusinformation(){
		return businformation;
	}

	@Override
 	public String toString(){
		return 
			"BusInformation{" + 
			"businformation = '" + businformation + '\'' + 
			"}";
		}
}