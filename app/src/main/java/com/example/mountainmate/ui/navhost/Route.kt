package com.example.mountainmate.ui.navhost

import kotlinx.serialization.Serializable

@Serializable
data object RouteHome

@Serializable
data object RouteSchedule

@Serializable
data class RouteItemList(val scheduleId: Int)

@Serializable
data class RouteWebView(val url: String)

@Serializable
data class RouteScheduleDetail(val title: String, val scheduleId: Int)