package com.ibm.medid.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.medid.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByName(String name);

    List<Bank> findAllByIsDeleteFalse();

    Optional<Bank> findByIdAndIsDeleteFalse(Long id);

}