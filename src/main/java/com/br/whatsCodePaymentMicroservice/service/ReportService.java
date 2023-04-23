package com.br.whatsCodePaymentMicroservice.service;

import com.br.whatsCodePaymentMicroservice.repository.PurchaseRepository;
import com.br.whatsCodePaymentMicroservice.request.ReportRequest;
import com.br.whatsCodePaymentMicroservice.response.ReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public List<ReportResponse> report (ReportRequest reportRequest){
        List<Object[]> resultList = new ArrayList<>();

        if(reportRequest.getFilterType() == 1) {
            resultList = purchaseRepository.findReportByDueDate(reportRequest.getInitalDate(), reportRequest.getFinalDate());
        } else if (reportRequest.getFilterType() == 2) {
            resultList = purchaseRepository.findReportByPaymentDate(reportRequest.getInitalDate(), reportRequest.getFinalDate());
        } else if (reportRequest.getFilterType() == 3) {
            resultList = purchaseRepository.findReportByCreditDate(reportRequest.getInitalDate(), reportRequest.getFinalDate());
        }

        List<ReportResponse> reportResponseList = new ArrayList<>();

        for(Object[] obj : resultList) {
            ReportResponse reportResponse = new ReportResponse();

            reportResponse.setCpf((String) obj[0]);
            reportResponse.setFullName((String) obj[1]);
            reportResponse.setPurchaseDate((LocalDate) obj[2]);
            reportResponse.setPaymentValue((BigDecimal) obj[3]);
            reportResponse.setInstallmentDueDate((LocalDate) obj[4]);
            reportResponse.setPaymentDate((LocalDate) obj[5]);
            reportResponse.setCreditDate((LocalDate) obj[6]);
            reportResponse.setInstallmentValue((BigDecimal) obj[7]);

            reportResponseList.add(reportResponse);
        }
        return reportResponseList;
    }
}
