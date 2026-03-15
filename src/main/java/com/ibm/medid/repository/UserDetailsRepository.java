package com.ibm.medid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.medid.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long>{

}
