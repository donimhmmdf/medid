package com.ibm.medid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping()
    public WebResponse<BankResponse> addBank(@RequestBody BankRequest request) {
        return WebResponse.<BankResponse>builder()
                .data(bankService.create(request))
                .message("Success")
                .build();
    }

    @GetMapping()
    public WebResponse<List<BankResponse>> getAll() {
        return WebResponse.<List<BankResponse>>builder()
                .data(bankService.findAll())
                .message("Success")
                .build();
    }

    @GetMapping("/{id}")
    public WebResponse<BankResponse> getOne(@PathVariable Long id) {
        return WebResponse.<BankResponse>builder()
                .data(bankService.findOne(id))
                .message("Success")
                .build();
    }

    @PutMapping("/{id}")
    public WebResponse<BankResponse> editOne(@PathVariable Long id, @RequestBody BankRequest bankRequest) {
        return WebResponse.<BankResponse>builder()
                .data(bankService.updateOne(id, bankRequest))
                .message("Success")
                .build();
    }

    @DeleteMapping("/{id}")
    public WebResponse<String> deleteOne(@PathVariable Long id) {
        return WebResponse.<String>builder()
                .data(bankService.removeOne(id))
                .message("Success")
                .build();
    }

}
