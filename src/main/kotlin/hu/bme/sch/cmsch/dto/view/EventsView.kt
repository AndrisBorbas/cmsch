package hu.bme.sch.cmsch.dto.view

import com.fasterxml.jackson.annotation.JsonView
import hu.bme.sch.cmsch.dto.Preview
import hu.bme.sch.cmsch.model.EventEntity

data class EventsView(

    @JsonView(Preview::class)
    val warningMessage: String = "",

    @JsonView(Preview::class)
    val allEvents: List<EventEntity> = listOf(),

)
