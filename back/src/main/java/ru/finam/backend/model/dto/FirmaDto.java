package ru.finam.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirmaDto {
    private String tickerName;
    private String name;
    private float price;
    private float capitalization;
    private float averageTradingVolume;
}
