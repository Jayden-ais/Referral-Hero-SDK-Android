package com.example.referralsdk

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.sdk.referral.RH
import com.sdk.referral.model.*
import com.sdk.referral.utils.DeviceInfo


class MainActivity : AppCompatActivity(), RH.RHReferralCallBackListener, View.OnClickListener,
    RH.RHMyReferralCallBackListener, RH.RHLeaderBoardReferralCallBackListener,
    RH.RHRewardCallBackListener {

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
    lateinit var btnReward: Button
    lateinit var btnReffer: Button
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
        btnReward = findViewById(R.id.btnReward)
        btnReffer = findViewById(R.id.btnReferrer)
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
        btnReward.setOnClickListener(this)
        btnReffer.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        //RH.instance?.fetchInstallReferrer(this)
    }

    override fun onFailureCallback(response: ApiResponse<SubscriberData>?) {
        Log.e("Response", Gson().toJson(response))
        txtReponse.text = "Response : " + response?.status
    }

    override fun onSuccessCallback(response: ApiResponse<SubscriberData>?) {
        Log.e("onSuccessCallback", Gson().toJson(response?.data))
        txtReponse.text = "Response : " + response?.status
    }

    override fun onClick(v: View) {
        val referralParams = ReferralParams()
        val rh = RH.instance

        when (v.id) {
            R.id.btnAdd -> {
                referralParams.email = "jayden@aistechnolabs.org"
                referralParams.domain = "https://wongazoma.aistechnolabs.info/action"
                referralParams.name = "Jayden"
                referralParams.referrer = ""
                referralParams.uuid = "MF8b0d590f9d"
                // referralParams.ip_address = rh?.deviceInfo?.getIpAddress()
                referralParams.device = rh?.deviceInfo?.getDeviceModel()
                referralParams.os_type = rh?.deviceInfo?.getOperatingSystem()
                referralParams.screen_size = rh?.deviceInfo?.getDeviceScreenSize()
                rh?.formSubmit(this, referralParams)
                rh?.prefHelper?.rHReferralLink
            }
            R.id.btnGet -> {

                rh?.getSubscriber(object : RH.RHReferralCallBackListener {
                    override fun onSuccessCallback(response: ApiResponse<SubscriberData>?) {
                        Log.e("Reponse", Gson().toJson(response))
                        Toast.makeText(
                            this@MainActivity,
                            response?.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onFailureCallback(response: ApiResponse<SubscriberData>?) {
                        TODO("Not yet implemented")
                    }

                })

            }
            R.id.btnDelete -> RH.instance?.deleteSubscriber(this)
            R.id.btnUpdate -> {
                referralParams.name = "AndiDevOps"
                rh?.updateSubscriber(this, referralParams)
            }
            R.id.btnTrack -> {
                referralParams.email = "jayden@aistechnolabs.org"
                referralParams.name = "AndiDev"
                rh?.trackReferral(this, referralParams)
            }
            R.id.btnCapture -> {
                referralParams.social = "Whatsapp"
                rh?.captureShare(this, referralParams)
            }
            R.id.btnGetReferral -> RH.instance?.getMyReferrals(this)
            R.id.btnGetCampaign -> RH.instance?.getLeaderboard(this)
            R.id.btnConfirm -> RH.instance?.confirmReferral(this)
            R.id.btnPending -> {
                referralParams.email = "jayden@aistechnolabs.org"
                referralParams.name = "AndiDev"
                referralParams.ip_address = DeviceInfo(this).getIpAddress()
                referralParams.screen_size = DeviceInfo(this).getDeviceScreenSize()
                referralParams.device = DeviceInfo(this).getOperatingSystem()
                referralParams.referrer = "";
                rh?.pendingReferral(this, referralParams)
            }
            R.id.btnOrgTrack -> {
                referralParams.email = "jayden@aistechnolabs.org"
                rh?.organicTrackReferral(this, referralParams)
            }
            R.id.btnReward -> {
                rh?.getRewards(this)
            }
            R.id.btnReferrer -> {
                rh?.getReferrer(this)
            }
        }

    }

    override fun onMyReferralSuccessCallback(response: ApiResponse<ListSubscriberData>?) {
        Log.e("onMyReferralSuccess", Gson().toJson(response))
    }

    override fun onMyReferralFailureCallback(response: ApiResponse<ListSubscriberData>?) {
        Log.e("onMyReferralSuccess", Gson().toJson(response))
    }

    override fun onLeaderBoardReferralSuccessCallback(response: ApiResponse<RankingDataContent>?) {
        Log.e("onLeaderBoardSuccess", Gson().toJson(response))
    }

    override fun onLeaderBoardReferralFailureCallback(response: ApiResponse<RankingDataContent>?) {
        Log.e("onLeaderBoardSuccess", Gson().toJson(response))
    }

    override fun onRewardSuccessCallback(response: ApiResponse<ListSubscriberData>?) {
        Log.e("onRewardSuccess", Gson().toJson(response))
    }

    override fun onRewardFailureCallback(response: ApiResponse<ListSubscriberData>?) {
        Log.e("onRewardSuccess", Gson().toJson(response))
    }


}

