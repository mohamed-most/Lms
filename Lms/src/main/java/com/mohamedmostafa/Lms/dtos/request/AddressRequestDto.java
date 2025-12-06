package com.mohamedmostafa.Lms.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Data
@Builder
public class AddressRequestDto {
    @NotBlank
    @NotNull
    String street;

    @NotBlank
    @NotNull
    String city;

    @NotBlank
    @NotNull
    String country;
}
