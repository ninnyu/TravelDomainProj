package com.example.potatopaloozac.traveldomainproj.ui.booking.payment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.potatopaloozac.traveldomainproj.R
import com.example.potatopaloozac.traveldomainproj.data.network.model.PaymentInfo
import com.example.potatopaloozac.traveldomainproj.ui.booking.businfo.BusInfoActivity
import com.example.potatopaloozac.traveldomainproj.ui.booking.passengerdetails.PassengerDetailsActivity
import com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo.SeatInfoActivity
import com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo2.SeatInfo2Activity
import com.example.potatopaloozac.traveldomainproj.ui.booking.transfer.TransferActivity
import com.paypal.android.sdk.payments.*
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_payment.*
import org.json.JSONException
import java.math.BigDecimal
import java.sql.Timestamp
import kotlin.math.log

class PaymentActivity : AppCompatActivity() {

    lateinit var paymentInfo: PaymentInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        paymentInfo = intent.getParcelableExtra("paymentinfo")

        if (paymentInfo.businfo2 != null) {
            paymentInfo.total = paymentInfo.businfo1.fare.toInt() * paymentInfo.seatCount_bus1 +
                    paymentInfo.businfo2.fare.toInt() * paymentInfo.seatCount_bus2
        } else {
            paymentInfo.total = paymentInfo.businfo1.fare.toInt() * paymentInfo.seatCount_bus1
        }

        var totalSeats = paymentInfo.seatCount_bus1 + paymentInfo.seatCount_bus2

        var start = paymentInfo.startCity.cityname + "\n" + paymentInfo.businfo1.busdeparturetime
        var end = paymentInfo.endCity.cityname + "\n" + paymentInfo.businfo1.dropingtime

        tv_payment_leavingFrom.text = start
        tv_payment_goingTo.text = end
        tv_payment_passengerNum.text = totalSeats.toString()
        tv_payment_perPerson.text = paymentInfo.businfo1.fare
        tv_payment_total.text = paymentInfo.total.toString()
    }

    fun displayResult(result: String) {
        val timestamp = Timestamp(System.currentTimeMillis())
        var time = timestamp.toString()
        time = time.substring(0,16)

        paymentInfo.time = time
        paymentInfo.id = result

        var i: Intent = Intent(this, PaymentConfirmationActivity::class.java)
        i.putExtra("paymentinfo", paymentInfo)
        startActivity(i)
    }

    fun onBuyPress(view: View) {
        val thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE)

        val intent = Intent(this@PaymentActivity, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy)
        startActivityForResult(intent, REQUEST_CODE_PAYMENT)

        if (BusInfoActivity.activity != null)
            BusInfoActivity.activity.finish()
        if (TransferActivity.activity != null)
            TransferActivity.activity.finish()
        if (SeatInfo2Activity.activity != null)
            SeatInfo2Activity.activity.finish()
        if (SeatInfoActivity.activity != null)
            SeatInfoActivity.activity.finish()
        if (PassengerDetailsActivity.activity != null)
            PassengerDetailsActivity.activity.finish()
    }

    private fun getThingToBuy(paymenT_INTENT_SALE: String): PayPalPayment {
        return PayPalPayment(BigDecimal(paymentInfo.total), "USD", "Bus Fare Total", paymenT_INTENT_SALE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                val confirm = data?.getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (confirm != null) {
                    try {
                        Log.i(TAG, confirm.toJSONObject().toString(4))
                        Log.i(TAG, confirm.toJSONObject().getJSONObject("response").getString("id"))
                        displayResult(confirm.toJSONObject().getJSONObject("response").getString("id"))
                    } catch (e: JSONException) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e)
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG, "The user canceled.")
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(TAG, "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.")
            }
        }
    }

    companion object {
        private val REQUEST_CODE_PAYMENT = 1
        private val TAG = "paypalactivitytag"

        private val CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK

        private val CONFIG_CLIENT_ID = "credentials from developer.paypal.com"

        private val config = PayPalConfiguration()
                .environment(CONFIG_ENVIRONMENT)
                .clientId(CONFIG_CLIENT_ID)
                .merchantName("Example Merchant")
                .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
                .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"))
    }

    public override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }
}