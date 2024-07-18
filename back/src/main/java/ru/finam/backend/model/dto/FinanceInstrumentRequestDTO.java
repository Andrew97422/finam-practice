package ru.finam.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// уточнить по поводу категории "Все"

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanceInstrumentRequestDTO {
    private String tickerName; // ""
    private String type; // ""
    private String sector; // ""
    private String priceFrom; // 1000
    private String priceUpTo; // 2000
    private String capitalizationFrom; //
    private String capitalizationUpTo; //
    private String volumeFrom; //
    private String volumeUpTo;//
}
