package com.example.referralsdk

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sdk.referral.RH
import com.sdk.referral.networking.*


class MainActivity : AppCompatActivity(), RH.RHReferralCallBackListener, View.OnClickListener,
    RH.RHMyReferralCallBackListener, RH.RHLeaderBoardReferralCallBackListener {

    lateinit var btnGet: Button
    lateinit var btnAdd: Button
    lateinit var btnDelete: Button
    lateinit var btnUpdate: Button
    lateinit var btnTrack: Button
    lateinit var btnOrgTrack: Button
    lateinit var btnPending: Button
    lateinit var btnConfirm: Button
    lateinit var btnGetCampaign: Button
    lateinit var btnGetReferral: Button
    lateinit var btnCapture: Button
    lateinit var txtReponse: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd = findViewById(R.id.btnAdd)
        btnGet = findViewById(R.id.btnGet)
        btnDelete = findViewById(R.id.btnDelete)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnTrack = findViewById(R.id.btnTrack)
        btnOrgTrack = findViewById(R.id.btnOrgTrack)
        btnPending = findViewById(R.id.btnPending)
        btnConfirm = findViewById(R.id.btnConfirm)
        btnGetCampaign = findViewById(R.id.btnGetCampaign)
        btnGetReferral = findViewById(R.id.btnGetReferral)
        btnCapture = findViewById(R.id.btnCapture)
        txtReponse = findViewById(R.id.txtReponse)
        btnAdd.setOnClickListener(this)
        btnGet.setOnClickListener(this)
        btnTrack.setOnClickListener(this)
        btnOrgTrack.setOnClickListener(this)
        btnPending.setOnClickListener(this)
        btnConfirm.setOnClickListener(this)
        btnGetCampaign.setOnClickListener(this)
        btnGetReferral.setOnClickListener(this)
        btnCapture.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
    }

    override fun onFailureCallback(response: ApiResponse<SubscriberData>?) {
        txtReponse.text = "Response : " + response?.status
    }

    override fun onSuccessCallback(response: ApiResponse<SubscriberData>?) {
        txtReponse.text = "Response : " + response?.message
    }

    override fun onClick(v: View) {
        val referralParams = ReferralParams()
        val rh = RH.instance

        when (v.id) {
            R.id.btnAdd -> {
                referralParams.email = "Jayden@gmail.com"
                referralParams.domain = "https://wongazoma.aistechnolabs.info/action"
                referralParams.name = "AndiDev"
                referralParams.referrer = ""
                referralParams.uuid = "MF4345c63888"
                RH.instance?.formSubmit(this, referralParams)
                RH.instance?.prefHelper?.rHReferralLink
            }
            R.id.btnGet -> RH.instance?.getSubscriber(this)
            R.id.btnDelete -> RH.instance?.deleteSubscriber(this)
            R.id.btnUpdate -> {
                referralParams.name = "AndiDevOps"
                RH.instance?.updateSubscriber(this, referralParams)
            }
            R.id.btnTrack -> {
                referralParams.email = "Jayden@gmail.com"
                referralParams.name = "AndiDev"
                RH.instance?.trackReferral(this, referralParams)
            }
            R.id.btnCapture -> {
                referralParams.social = "Whatsapp"
                RH.instance?.captureShare(this, referralParams)
            }
            R.id.btnGetReferral -> RH.instance?.getMyReferrals(this)
            R.id.btnGetCampaign -> RH.instance?.getLeaderboard(this)
        }
    }

    override fun onMyReferralSuccessCallback(response: ApiResponse<ListSubscriberData>?) {
        response?.data?.response?.let { Log.e("onMyReferralSuccess", it) }
    }

    override fun onMyReferralFailureCallback(response: ApiResponse<ListSubscriberData>?) {
        response?.message?.let { Log.e("onMyReferralSuccess", it) }
    }

    override fun onLeaderBoardReferralSuccessCallback(response: ApiResponse<RankingDataContent>?) {
        Log.e("onLeaderBoardSuccess", response?.data?.count.toString())
    }

    override fun onLeaderBoardReferralFailureCallback(response: ApiResponse<RankingDataContent>?) {
        response?.message?.let { Log.e("onLeaderBoardSuccess", it) }
    }


}

