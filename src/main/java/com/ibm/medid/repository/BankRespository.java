package com.ibm.medid.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.medid.model.Bank;

public interface BankRespository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByName(String name);

}