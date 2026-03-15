package com.ibm.medid.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ibm.medid.dto.BankRequest;
import com.ibm.medid.dto.BankResponse;
import com.ibm.medid.model.Bank;
import com.ibm.medid.repository.BankRepository;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ValidationService validation;

    public BankResponse create(BankRequest request) {
        validation.validate(request);

        Bank bank = new Bank();
        bank.setName(request.getName());
        bank.setVaCode(request.getVaCode());
        // bank.setCreatedAt(Instant.now());

        Bank savedBank = bankRepository.save(bank);
        return toBankResponse(savedBank);
    }

    public List<BankResponse> findAll() {
        List<Bank> banks = bankRepository.findAllByIsDeleteFalse();
        List<BankResponse> bankResponses = new ArrayList<>();

        if (banks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank data empty.");
        }

        for (Bank bank : banks) {
            bankResponses.add(toBankResponse(bank));
        }

        return bankResponses;
    }

    public BankResponse findOne(Long id) {
        Bank bank = bankRepository.findByIdAndIsDeleteFalse(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank with id " + id + " not found."));
        return toBankResponse(bank);
    }

    public String removeOne(Long id) {
        Bank bank = bankRepository.findByIdAndIsDeleteFalse(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank with id " + id + " not found."));
        bank.setIsDelete(true);
        bankRepository.save(bank);
        return "OK";
    }

    public BankResponse updateOne(Long id, BankRequest request) {
        validation.validate(request);
        Bank bank = bankRepository.findByIdAndIsDeleteFalse(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank with id " + id
                        + " not found."));

        bank.setName(request.getName());
        bank.setVaCode(request.getVaCode());
        bank.setCreatedAt(Instant.now());
        Bank savedBank = bankRepository.save(bank);

        return toBankResponse(savedBank);
    }

    public BankResponse toBankResponse(Bank bank) {
        return BankResponse.builder()
                .id(bank.getId())
                .name(bank.getName())
                .vaCode(bank.getVaCode())
                .build();
    }

}
