package ru.finam.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinanceInstrumentResponseDTO {
    private String ticker;
    private String name;
    private float price;
    private float capitalization;
    private float average_trading_volume;
}
