package com.mohamedmostafa.Lms.controllers;


import com.mohamedmostafa.Lms.dtos.request.AddressRequestDto;
import com.mohamedmostafa.Lms.dtos.response.AddressResponseDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.services.abstracts.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/me/address")
public class AddressController {


    public AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponse<AddressResponseDto>> addAddress(@Valid @RequestBody
                                                                      AddressRequestDto addressRequestDto) {
        return ApiResponse.success(addressService.addAddress(addressRequestDto), HttpStatus.CREATED);
    }

    @GetMapping({"/", ""})
    public ResponseEntity<ApiResponse<AddressResponseDto>> getAddress() {
        return ApiResponse.success(addressService.getAddress(), HttpStatus.OK);
    }


}
