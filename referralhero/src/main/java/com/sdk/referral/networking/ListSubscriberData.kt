package com.sdk.referral.networking

import com.sdk.referral.model.SubscriberData

data class ListSubscriberData(
    val response: String,
    val subscribers: List<SubscriberData>,
    val rewards: List<Reward>,
    val pagination: Pagination
)

data class Reward(
    val id: Long,
    val name: String,
    val status: String,
    val referrals: Any?,
    val createdAt: Long,
    val signupType: String,
    val referralsType: Any?,
    val recurringCount: Any?,
)

data class Pagination(
    val total_pages: Int, val current_page: Int, val per_page: Int, val total_objects: Int
)
