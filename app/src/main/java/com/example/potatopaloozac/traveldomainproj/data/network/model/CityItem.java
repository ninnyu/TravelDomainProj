package com.example.potatopaloozac.traveldomainproj.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CityItem implements Parcelable {

	@SerializedName("cityname")
	private String cityname;

	@SerializedName("citylongtitude")
	private String citylongtitude;

	@SerializedName("citylatitude")
	private String citylatitude;

	public CityItem() {
	}

	protected CityItem(Parcel in) {
		cityname = in.readString();
		citylongtitude = in.readString();
		citylatitude = in.readString();
	}

	public static final Creator<CityItem> CREATOR = new Creator<CityItem>() {
		@Override
		public CityItem createFromParcel(Parcel in) {
			return new CityItem(in);
		}

		@Override
		public CityItem[] newArray(int size) {
			return new CityItem[size];
		}
	};

	public void setCityname(String cityname){
		this.cityname = cityname;
	}

	public String getCityname(){
		return cityname;
	}

	public void setCitylongtitude(String citylongtitude){
		this.citylongtitude = citylongtitude;
	}

	public String getCitylongtitude(){
		return citylongtitude;
	}

	public void setCitylatitude(String citylatitude){
		this.citylatitude = citylatitude;
	}

	public String getCitylatitude(){
		return citylatitude;
	}

	@Override
 	public String toString(){
		return 
			"CityItem{" + 
			"cityname = '" + cityname + '\'' + 
			",citylongtitude = '" + citylongtitude + '\'' + 
			",citylatitude = '" + citylatitude + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(cityname);
		dest.writeString(citylongtitude);
		dest.writeString(citylatitude);
	}
}