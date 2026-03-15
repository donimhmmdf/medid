package com.ibm.medid.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.medid.dto.BankRequest;
import com.ibm.medid.dto.BankResponse;
import com.ibm.medid.model.Bank;
import com.ibm.medid.repository.BankRespository;

@Service
public class BankService {

    @Autowired
    private BankRespository bankRespository;

    public BankResponse create(BankRequest request) {

        Bank bank = new Bank();

        bank.setName(request.getName());
        bank.setVaCode(request.getVaCode());
        bank.setCreatedAt(Instant.now());

        bankRespository.save(bank);

        return toBankResponse(bank);

    }

    public BankResponse toBankResponse(Bank bank) {
        return BankResponse.builder()
                .id(bank.getId())
                .name(bank.getName())
                .vaCode(bank.getVaCode())
                .build();
    }

}
