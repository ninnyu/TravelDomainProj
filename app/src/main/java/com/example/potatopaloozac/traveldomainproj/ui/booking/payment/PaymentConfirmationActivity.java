package com.example.potatopaloozac.traveldomainproj.ui.booking.payment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.PaymentInfo;
import com.example.potatopaloozac.traveldomainproj.ui.gameschedule.GameScheduleActivity;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentConfirmationActivity extends AppCompatActivity {

    private static final String TAG = "PayConfirmActivityTAG";

    @BindView(R.id.tv_confirmationSummary)
    TextView tvConfirmationSummary;
    @BindView(R.id.iv_confirmationQR)
    ImageView ivConfirmationQR;

    private PaymentInfo paymentInfo;
    private File file;
    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);
        ButterKnife.bind(this);

        paymentInfo = getIntent().getParcelableExtra("paymentinfo");

        String email = MySharedPreference.readString(MySharedPreference.USER_EMAIL, "");
        int totalSeats = paymentInfo.getSeatCount_bus1() + paymentInfo.getSeatCount_bus2();

        String s = "Confirmation Number: \n\t\t\t" + paymentInfo.getId() +
                "\nTicket Booked On: \t" + paymentInfo.getTime() +
                "\nBus ID: \t\t\t\t\t\t\t\t\t\t\t\t\t" + paymentInfo.getBusinfo1().getBusid() +
                "\nDeparture: \t\t\t\t\t\t\t\t\t\t" + paymentInfo.getBusinfo1().getBusdeparturetime() +
                "\nArrival: \t\t\t\t\t\t\t\t\t\t\t\t\t" + paymentInfo.getBusinfo1().getDropingtime() +
                "\nTotal Passengers: \t\t\t" + totalSeats +
                "\nTotal Fare: \t\t\t\t\t\t\t\t\t\t" + paymentInfo.getTotal();

        tvConfirmationSummary.setText(s);

        String s2 = MySharedPreference.readString(MySharedPreference.USER_ID, "")
                + paymentInfo.getBusinfo1().getBusid()
                + paymentInfo.getId();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(s2, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            ivConfirmationQR.setImageBitmap(bitmap);
            file = savebitmap(bitmap);
            Log.d(TAG, "onCreate: ");
        } catch (WriterException e) {
            Log.d(TAG, "onCreate: " + e.toString());
            e.printStackTrace();
        }

        try {
            Message m = buildMessage(
                    createSessionObject(),
                    /*TODO email*/,
                    MySharedPreference.readString(MySharedPreference.USER_EMAIL, ""),
                    "Roat Trip Bus Ticket Confirmation",
                    "Here is the confirmation for your bus ticket reservation made on Road Trip!\n",
                    file.toString());
            new PaymentConfirmationActivity.SendMailTask().execute(m);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.bt_confirmationViewGame)
    public void onViewClicked() {
        Intent i = new Intent(this, GameScheduleActivity.class);
        startActivity(i);
    }

    private File savebitmap(Bitmap bitmap) {
        String dir = Environment.getExternalStorageDirectory().toString();
        OutputStream outputStream = null;
        File file = new File(dir, "temp.png");

        if (file.exists()) {
            file.delete();
            file = new File(dir, "temp.png");
        }

        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    private static Message buildMessage(
            Session session, String from, String recipients, String subject, String text, String filename)
            throws MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        message.setSubject(subject);

        BodyPart messageTextPart = new MimeBodyPart();
        messageTextPart.setText(text);

        BodyPart messageAttachmentPart = new MimeBodyPart();
        DataSource source = new FileDataSource(new File(filename));
        messageAttachmentPart.setDataHandler(new DataHandler(source));
        messageAttachmentPart.setFileName(filename);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageTextPart);
        multipart.addBodyPart(messageAttachmentPart);
        message.setContent(multipart);

        return message;
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(/*TODO email*/);
            }
        });
    }

    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(PaymentConfirmationActivity.this, "Please wait",
                    "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}