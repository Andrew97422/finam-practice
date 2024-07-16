package ru.finam.backend.model.dto;
import lombok.Builder;
import lombok.Data;

@Data
public class FirmaDto {
    private String tickerName;
    private String name;
    private float price;
    private float capitalization;
    private float averageTradingVolume;
}
