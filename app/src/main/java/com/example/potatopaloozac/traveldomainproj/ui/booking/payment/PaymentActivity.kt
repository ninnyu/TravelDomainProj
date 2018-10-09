package com.example.potatopaloozac.traveldomainproj.ui.booking.payment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.potatopaloozac.traveldomainproj.R
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference.END_CITY_NAME
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference.START_CITY_NAME
import com.paypal.android.sdk.payments.*
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_payment.*
import org.json.JSONException
import java.math.BigDecimal
import java.sql.Timestamp

class PaymentActivity : AppCompatActivity() {

    lateinit var businformationItem: BusinformationItem
    var seatCount = 0
    var total = 0
    lateinit var time: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        businformationItem = intent.getParcelableExtra<BusinformationItem>("businfo")
        seatCount = intent.getIntExtra("seatcount", 0)
        total = businformationItem.fare.toInt() * seatCount
        var start = MySharedPreference.readString(START_CITY_NAME, "") + "\n" + businformationItem.busdeparturetime
        var end = MySharedPreference.readString(END_CITY_NAME, "") + "\n" + businformationItem.dropingtime

        tv_payment_leavingFrom.text = start
        tv_payment_goingTo.text = end
        tv_payment_passengerNum.text = seatCount.toString()
        tv_payment_perPerson.text = businformationItem.fare
        tv_payment_total.text = total.toString()
    }

    fun displayResult(result: String) {
        val timestamp = Timestamp(System.currentTimeMillis())
        val time = timestamp.toString()

        var i: Intent = Intent(this, PaymentConfirmationActivity::class.java)
        i.putExtra("businfo", businformationItem)
        i.putExtra("seatcount", seatCount)
        i.putExtra("total", total)
        i.putExtra("time", time)
        i.putExtra("paymentid", result)
        startActivity(i)
    }

    fun onBuyPress(view: View) {
        val thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE)

        /*
         * See getStuffToBuy(..) for examples of some available payment options.
         */

        val intent = Intent(this@PaymentActivity, PaymentActivity::class.java)

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy)

        startActivityForResult(intent, REQUEST_CODE_PAYMENT)
    }

    private fun getThingToBuy(paymenT_INTENT_SALE: String): PayPalPayment {     //PayPalPayment -> return type
        return PayPalPayment(BigDecimal(total), "USD", "Bus Fare Total", paymenT_INTENT_SALE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                val confirm = data?.getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (confirm != null) {
                    try {
                        Log.i(TAG, confirm.toJSONObject().toString(4))
                        //Log.i(TAG, confirm.payment.toJSONObject().toString(4))
                        /**
                         * TODO: send 'confirm' (and possibly confirm.getPayment() to your server for verification
                         * or consent completion.
                         * See https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                         * for more details.
                         * For sample mobile backend interactions, see
                         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
                         */
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

    //companion object for use of singleton classes
    companion object {
        private val REQUEST_CODE_PAYMENT = 1
        private val TAG = "paypaltag"

        private val CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK     //need client id for ENVIRONMENT_SANDBOX, but not for ENVIRONMENT_NO_NETWORK

        // note that these credentials will differ between live & sandbox environments.
        private val CONFIG_CLIENT_ID = "credentials from developer.paypal.com"

        private val config = PayPalConfiguration()
                .environment(CONFIG_ENVIRONMENT)
                .clientId(CONFIG_CLIENT_ID)
                .merchantName("Example Merchant")
                .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
                .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"))
    }

    public override fun onDestroy() {
        // Stop service when done
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }
}
