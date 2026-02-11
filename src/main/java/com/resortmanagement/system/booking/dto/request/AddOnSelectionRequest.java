package com.resortmanagement.system.booking.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddOnSelectionRequest {
    private UUID addOnId;
    private Integer quantity;
}
