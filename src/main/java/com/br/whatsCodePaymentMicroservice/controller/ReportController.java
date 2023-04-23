package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.model.Purchase;
import com.br.whatsCodePaymentMicroservice.request.ReportRequest;
import com.br.whatsCodePaymentMicroservice.response.ReportResponse;
import com.br.whatsCodePaymentMicroservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService service;


    @GetMapping
    public ResponseEntity<List<ReportResponse>> findAll(@RequestParam("initalDate") String initalDate, @RequestParam("finalDate") String finalDate, @RequestParam("filterType") int filterType) {
    	ReportRequest request = new ReportRequest();
    	request.setInitalDate(LocalDate.parse(initalDate));
    	request.setFinalDate(LocalDate.parse(finalDate));
    	request.setFilterType(filterType);
        return ResponseEntity.status(HttpStatus.OK).body(service.report(request));
    }

}
