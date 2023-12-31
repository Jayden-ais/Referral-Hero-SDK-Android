package com.sdk.referral.model

data class ApiResponse<T>(
    val status: String?,
    val message: String?,
    val code: String?,
    val data: T?,
    val calls_left: Int?,
    val timestamp: Long?
)