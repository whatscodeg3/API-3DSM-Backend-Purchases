package com.br.whatsCodePaymentMicroservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportRequest {

    @NotNull
    private LocalDate initalDate;

    @NotNull
    private LocalDate finalDate;

    @NotNull
    private Integer filterType;
}
