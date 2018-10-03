package com.example.potatopaloozac.traveldomainproj.data.network.model;

import com.google.gson.annotations.SerializedName;

public class RouteItem{

	@SerializedName("route-startpoint-latitude")
	private String routeStartpointLatitude;

	@SerializedName("route-destination")
	private String routeDestination;

	@SerializedName("route-endpoint-latitude")
	private String routeEndpointLatitude;

	@SerializedName("route-startfrom")
	private String routeStartfrom;

	@SerializedName("id")
	private String id;

	@SerializedName("route-startpoint-longitude")
	private String routeStartpointLongitude;

	@SerializedName("route-endpoint-longiude")
	private String routeEndpointLongiude;

	@SerializedName("routename")
	private String routename;

	public void setRouteStartpointLatitude(String routeStartpointLatitude){
		this.routeStartpointLatitude = routeStartpointLatitude;
	}

	public String getRouteStartpointLatitude(){
		return routeStartpointLatitude;
	}

	public void setRouteDestination(String routeDestination){
		this.routeDestination = routeDestination;
	}

	public String getRouteDestination(){
		return routeDestination;
	}

	public void setRouteEndpointLatitude(String routeEndpointLatitude){
		this.routeEndpointLatitude = routeEndpointLatitude;
	}

	public String getRouteEndpointLatitude(){
		return routeEndpointLatitude;
	}

	public void setRouteStartfrom(String routeStartfrom){
		this.routeStartfrom = routeStartfrom;
	}

	public String getRouteStartfrom(){
		return routeStartfrom;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setRouteStartpointLongitude(String routeStartpointLongitude){
		this.routeStartpointLongitude = routeStartpointLongitude;
	}

	public String getRouteStartpointLongitude(){
		return routeStartpointLongitude;
	}

	public void setRouteEndpointLongiude(String routeEndpointLongiude){
		this.routeEndpointLongiude = routeEndpointLongiude;
	}

	public String getRouteEndpointLongiude(){
		return routeEndpointLongiude;
	}

	public void setRoutename(String routename){
		this.routename = routename;
	}

	public String getRoutename(){
		return routename;
	}

	@Override
 	public String toString(){
		return 
			"RouteItem{" + 
			"route-startpoint-latitude = '" + routeStartpointLatitude + '\'' + 
			",route-destination = '" + routeDestination + '\'' + 
			",route-endpoint-latitude = '" + routeEndpointLatitude + '\'' + 
			",route-startfrom = '" + routeStartfrom + '\'' + 
			",id = '" + id + '\'' + 
			",route-startpoint-longitude = '" + routeStartpointLongitude + '\'' + 
			",route-endpoint-longiude = '" + routeEndpointLongiude + '\'' + 
			",routename = '" + routename + '\'' + 
			"}";
		}
}