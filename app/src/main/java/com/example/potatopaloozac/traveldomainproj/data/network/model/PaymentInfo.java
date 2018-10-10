package com.example.potatopaloozac.traveldomainproj.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentInfo implements Parcelable {

    private BusinformationItem businfo1, businfo2;
    private CityItem startCity, endCity, transferCity;
    private String id, time;
    private int seatCount_bus1, seatCount_bus2, total;

    public PaymentInfo() {
    }

    protected PaymentInfo(Parcel in) {
        businfo1 = in.readParcelable(BusinformationItem.class.getClassLoader());
        businfo2 = in.readParcelable(BusinformationItem.class.getClassLoader());
        startCity = in.readParcelable(CityItem.class.getClassLoader());
        endCity = in.readParcelable(CityItem.class.getClassLoader());
        transferCity = in.readParcelable(CityItem.class.getClassLoader());
        id = in.readString();
        time = in.readString();
        seatCount_bus1 = in.readInt();
        seatCount_bus2 = in.readInt();
        total = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(businfo1, flags);
        dest.writeParcelable(businfo2, flags);
        dest.writeParcelable(startCity, flags);
        dest.writeParcelable(endCity, flags);
        dest.writeParcelable(transferCity, flags);
        dest.writeString(id);
        dest.writeString(time);
        dest.writeInt(seatCount_bus1);
        dest.writeInt(seatCount_bus2);
        dest.writeInt(total);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PaymentInfo> CREATOR = new Creator<PaymentInfo>() {
        @Override
        public PaymentInfo createFromParcel(Parcel in) {
            return new PaymentInfo(in);
        }

        @Override
        public PaymentInfo[] newArray(int size) {
            return new PaymentInfo[size];
        }
    };

    public BusinformationItem getBusinfo1() {
        return businfo1;
    }

    public void setBusinfo1(BusinformationItem businfo1) {
        this.businfo1 = businfo1;
    }

    public BusinformationItem getBusinfo2() {
        return businfo2;
    }

    public void setBusinfo2(BusinformationItem businfo2) {
        this.businfo2 = businfo2;
    }

    public CityItem getStartCity() {
        return startCity;
    }

    public void setStartCity(CityItem startCity) {
        this.startCity = startCity;
    }

    public CityItem getEndCity() {
        return endCity;
    }

    public void setEndCity(CityItem endCity) {
        this.endCity = endCity;
    }

    public CityItem getTransferCity() {
        return transferCity;
    }

    public void setTransferCity(CityItem transferCity) {
        this.transferCity = transferCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSeatCount_bus1() {
        return seatCount_bus1;
    }

    public void setSeatCount_bus1(int seatCount_bus1) {
        this.seatCount_bus1 = seatCount_bus1;
    }

    public int getSeatCount_bus2() {
        return seatCount_bus2;
    }

    public void setSeatCount_bus2(int seatCount_bus2) {
        this.seatCount_bus2 = seatCount_bus2;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "businfo1=" + businfo1 +
                ", businfo2=" + businfo2 +
                ", startCity=" + startCity +
                ", endCity=" + endCity +
                ", transferCity=" + transferCity +
                ", id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", seatCount_bus1=" + seatCount_bus1 +
                ", seatCount_bus2=" + seatCount_bus2 +
                ", total=" + total +
                '}';
    }
}
