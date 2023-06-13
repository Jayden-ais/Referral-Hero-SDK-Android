package com.sdk.referral.networking



data class Root(
    val status: String?,
    val data: List<VisitorReferral>,
    val callsLeft: Any?,
    val timestamp: Long??,
)

data class VisitorReferral(
    val id: String?,
    val name: String?,
    val email: String?,
    val phoneNumber: String?,
    val cryptoWalletAddress: String?,
    val cryptoWalletProvider: String?,
    val extraField: String?,
    val extraField2: String?,
    val optionField: String?,
    val conversionAmount: Double?,
    val code: String?,
    val position: Long?,
    val referred: Boolean?,
    val referredBy: ReferredBy,
    val peopleReferred: Long?,
    val promoted: Boolean?,
    val promotedAt: Any?,
    val verified: Boolean?,
    val verifiedAt: Any?,
    val points: Long?,
    val riskLevel: Long?,
    val host: String?,
    val source: Any?,
    val device: String?,
    val referralLink: String?,
    val createdAt: Long?,
    val lastUpdatedAt: Long?,
    val osType: String?,
    val screenSize: String?,
    val ipAddress: String?,
    val universalLink: String?,
    val response: String?,
)
