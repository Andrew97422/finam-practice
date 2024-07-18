package ru.finam.backend.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Таблица с данными по компаниям")
public class FinanceInstrumentResponseDTO {

    @Schema(description = "Тикер", name = "ticker")
    private String ticker;

    @Schema(description = "Наименование", name = "name")
    private String name;

    @Schema(description = "Цена", name = "price")
    private float price;

    @Schema(description = "Капитализация", name = "capitalization")
    private float capitalization;

    @Schema(description = "Ср. объем торгов", name = "averageTradingVolume")
    private float averageTradingVolume;

}
