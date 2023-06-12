package com.prmto.poxedexkmm.home.data.mapper

import com.prmto.poxedexkmm.home.data.model.dto.TypeDto
import com.prmto.poxedexkmm.home.domain.model.Type

expect fun TypeDto.toType(): Type
