package hu.bme.sch.cmsch.dto.view

import com.fasterxml.jackson.annotation.JsonView
import hu.bme.sch.cmsch.dto.FullDetails

data class RiddleHintView(

    @JsonView(FullDetails::class)
    var hint: String = ""

)
