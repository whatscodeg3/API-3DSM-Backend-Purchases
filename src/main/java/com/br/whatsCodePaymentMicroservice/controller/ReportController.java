package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.model.Purchase;
import com.br.whatsCodePaymentMicroservice.request.ReportRequest;
import com.br.whatsCodePaymentMicroservice.response.ReportResponse;
import com.br.whatsCodePaymentMicroservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService service;


    @GetMapping
    public ResponseEntity<List<ReportResponse>> findAll(@RequestBody ReportRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.report(request));
    }

}
