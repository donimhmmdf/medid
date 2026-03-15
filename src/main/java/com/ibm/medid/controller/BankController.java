package com.ibm.medid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.medid.dto.BankRequest;
import com.ibm.medid.dto.BankResponse;
import com.ibm.medid.dto.WebResponse;
import com.ibm.medid.service.BankService;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping()
    public String getMethodName() {
        return "Hello";
    }

    @PostMapping()
    public WebResponse<BankResponse> addBank(@RequestBody BankRequest request) {

        return WebResponse.<BankResponse>builder()
                .data(bankService.create(request))
                .message("Success")
                .build();

    }

}
