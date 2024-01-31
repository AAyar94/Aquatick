package com.aayar94.core.domain.usercase

import java.time.LocalTime

class CalculateReminderTimes {

    operator fun invoke(
        getUpHour: Int,
        getUpMin: Int,
        bedTimeHour: Int,
        bedTimeMin: Int,
        separatorMinute: Long
    ): MutableList<LocalTime> {
        val getUpTime = LocalTime.of(getUpHour, getUpMin)
        val goingBedTime = LocalTime.of(bedTimeHour, bedTimeMin)
        val notificationTime = mutableListOf<LocalTime>()

        var currentTime = getUpTime
        while (currentTime.isBefore(goingBedTime)) {
            notificationTime.add(currentTime)
            currentTime = currentTime.plusMinutes(separatorMinute)
        }

        return notificationTime
    }
}