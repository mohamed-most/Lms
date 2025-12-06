package com.mohamedmostafa.Lms.services.concretes;


import com.mohamedmostafa.Lms.dtos.request.AddressRequestDto;
import com.mohamedmostafa.Lms.dtos.response.AddressResponseDto;
import com.mohamedmostafa.Lms.entity.Address;
import com.mohamedmostafa.Lms.entity.User;
import com.mohamedmostafa.Lms.entity.UserDetailsCustomized;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mappers.AddressMapper;
import com.mohamedmostafa.Lms.repositories.AddressRepo;
import com.mohamedmostafa.Lms.repositories.UserRepo;
import com.mohamedmostafa.Lms.services.abstracts.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AddressServiceImp implements AddressService {

    private final AddressRepo addressRepo;
    private final UserRepo userRepo;

    @Autowired
    public AddressServiceImp(AddressRepo addressRepo, UserRepo userRepo) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }

    public AddressResponseDto addAddress(AddressRequestDto addressRequestDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsCustomized userDetails = (UserDetailsCustomized) auth.getPrincipal();

        User user = userRepo.findById(userDetails.getUserId())
                .orElseThrow(() -> new ResourceNotFoundEx("User not found"));

        Address address = AddressMapper.toEntity(addressRequestDto);
        address.setUser(user);
        Address savedAddress = addressRepo.save(address);

        return AddressMapper.toResponseDto(savedAddress);
    }


    public AddressResponseDto getAddress() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsCustomized userDetails = (UserDetailsCustomized) auth.getPrincipal();

        Address address = addressRepo.findByUserId(userDetails.getUserId())
                .orElseThrow(() -> new ResourceNotFoundEx("Address not found"));

        return AddressMapper.toResponseDto(address);
    }


}
