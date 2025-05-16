package com.dummy.dummy_endpoints.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DeleteCardsDro {
    @NotNull(message = "IDs cannot be null")
    private List<Long> ids;
}
