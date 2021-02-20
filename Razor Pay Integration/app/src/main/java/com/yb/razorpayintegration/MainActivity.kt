package com.yb.razorpayintegration

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Checkout.preload(applicationContext)
        btnOpen.setOnClickListener {
            startPayment()
        }
    }

    private fun startPayment() {
        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name", "YB Developers")
            options.put("description", "Testing Purpose")
            //You can omit the image option to fetch the image from dashboard
            options.put("currency", "INR");
            options.put("amount", "500")//pass amount in currency subunits

            val prefill = JSONObject()
            //add your email
            prefill.put("email", "abcd@gmail.com")
            //add your mobile number for OTP
            prefill.put("contact", "9999999999")
            options.put("prefill", prefill)
            co.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentError(i: Int, string: String?) {
        Toast.makeText(this@MainActivity, "Payment Failed..!! $string", Toast.LENGTH_LONG).show()
    }

    override fun onPaymentSuccess(string: String?) {
        Toast.makeText(this@MainActivity, "Payment Success..!!", Toast.LENGTH_LONG).show()
    }
}