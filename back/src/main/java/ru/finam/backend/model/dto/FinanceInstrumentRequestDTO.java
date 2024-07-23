package ru.finam.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanceInstrumentRequestDTO {
    private String tickerName;
    private String type;
    private String sector;
    private String priceFrom;
    private String priceUpTo;
    private String capitalizationFrom;
    private String capitalizationUpTo;
    private String volumeFrom;
    private String volumeUpTo;
}
