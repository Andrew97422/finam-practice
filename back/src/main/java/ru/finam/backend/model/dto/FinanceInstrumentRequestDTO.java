package ru.finam.backend.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Фильтры", description = "Выбирается пользователем")
public class FinanceInstrumentRequestDTO {

    @Schema(description = "Наименование", name = "tickerName", example = "SBER")
    private String tickerName;

    @Schema(description = "Тип инструмента", name = "type", example = "Акции")
    private String type;

    @Schema(description = "Сектор", name = "sector", example = "Финансы")
    private String sector;

    @Schema(description = "Цена от", name = "priceFrom", example = "0")
    private String priceFrom;

    @Schema(description = "Цена до", name = "priceUpTo", example = "300000")
    private String priceUpTo;

    @Schema(description = "Капитализация от", name = "capitalizationFrom", example = "0")
    private String capitalizationFrom;

    @Schema(description = "Капитализация до", name = "capitalizationUpTo", example = "7000000000000")
    private String capitalizationUpTo;

    @Schema(description = "Средний объем торгов от", name = "volumeFrom", example = "0")
    private String volumeFrom;

    @Schema(description = "Средний объем торгов до", name = "volumeUpTo", example = "1")
    private String volumeUpTo;

}
