package com.example.potatopaloozac.traveldomainproj.ui.booking.transfer;

import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;

public interface IViewTransfer {

   void showTransfer(String city_nm);
   void setCityInfo(String city_info);
   void showStartTransRoute(String info);
   void showTransDesRoute(String info);
   void setBusInfoStartTrans(BusinformationItem busInfo);
   void setBusInfoTransDes(BusinformationItem busInfo);
}
