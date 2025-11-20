package com.mohamedmostafa.Lms.repository;

import com.mohamedmostafa.Lms.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepo extends JpaRepository<Address,Integer> {
}
