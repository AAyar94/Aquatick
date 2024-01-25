package com.aayar94.core.domain.usercase

import java.time.LocalTime

class CalculateReminderTimes {

    operator fun invoke(
        getUpHour: Int,
        getUpMin: Int,
        BedTimeHour: Int,
        bedTimeMin: Int,
        separatorMinute: Long
    ): MutableList<LocalTime> {
        val getUpTime = LocalTime.of(getUpHour, getUpMin)
        val goingBedTime = LocalTime.of(BedTimeHour, bedTimeMin)
        val notificationTime = mutableListOf<LocalTime>()

        var currentTime = getUpTime
        while (currentTime.isBefore(goingBedTime)) {
            notificationTime.add(currentTime)
            currentTime = currentTime.plusMinutes(separatorMinute)
        }

        return notificationTime
    }
}