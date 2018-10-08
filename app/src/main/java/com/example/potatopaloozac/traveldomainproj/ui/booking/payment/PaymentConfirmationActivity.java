package com.example.potatopaloozac.traveldomainproj.ui.booking.payment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.ui.gameschedule.GameScheduleActivity;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.sql.Timestamp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentConfirmationActivity extends AppCompatActivity {

    private static final String TAG = "PayConfirmActivityTAG";

    @BindView(R.id.tv_confirmationSummary)
    TextView tvConfirmationSummary;
    @BindView(R.id.iv_confirmationQR)
    ImageView ivConfirmationQR;

    private BusinformationItem businformationItem;
    private int seatCount;
    private int total;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);
        ButterKnife.bind(this);

        businformationItem = getIntent().getParcelableExtra("businfo");
        seatCount = getIntent().getIntExtra("seatcount", 0);
        total = getIntent().getIntExtra("total", 0);
        time = getIntent().getStringExtra("time");

        String s = "Bus ID: " + businformationItem.getBusid() +
                "\nTicket Booked On: \t" + time +
                "\nDeparture: \t\t\t\t" + businformationItem.getBusdeparturetime() +
                "\nArrival: \t\t\t\t" + businformationItem.getDropingtime() +
                "\nTotal Amount of Passengers: \t" + seatCount +
                "\nTotal Fare: \t\t\t\t" + total;

        tvConfirmationSummary.setText(s);

        String s2 = MySharedPreference.readString(MySharedPreference.USER_ID, "") + businformationItem.getBusid();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            Log.d(TAG, "onCreate: success");
            BitMatrix bitMatrix = multiFormatWriter.encode(s2, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            ivConfirmationQR.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.d(TAG, "onCreate: " + e.toString());
            e.printStackTrace();
        }
    }

    @OnClick(R.id.bt_confirmationViewGame)
    public void onViewClicked() {
        Intent i = new Intent(this, GameScheduleActivity.class);
        i.putExtra("businfo", businformationItem);
        startActivity(i);
    }
}
