package com.example.llave360.model

import java.time.Duration
import java.time.Instant

data class TimeSlot(
  val startAt: Instant,
  val endAt: Instant,
  val resourceId: String? = null,
) {
  init {
    require(endAt.isAfter(startAt)) { "endAt must be after startAt" }
  }

  val durationMinutes: Long
    get() = Duration.between(startAt, endAt).toMinutes()

  fun canFit(serviceDurationMinutes: Int): Boolean =
    serviceDurationMinutes > 0 && durationMinutes >= serviceDurationMinutes
}
