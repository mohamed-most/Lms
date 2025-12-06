package com.mohamedmostafa.Lms.repositories;

import com.mohamedmostafa.Lms.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {
    Optional<Address> findByUserId(Integer userId);
}
