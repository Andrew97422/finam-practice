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

    @Schema(description = "Наименование", name = "tickerName")
    private String tickerName;

    @Schema(description = "Тип инструмента", name = "type")
    private String type;

    @Schema(description = "Сектор", name = "sector")
    private String sector;

    @Schema(description = "Цена от", name = "priceFrom")
    private String priceFrom;

    @Schema(description = "Цена до", name = "priceUpTo")
    private String priceUpTo;

    @Schema(description = "Капитализация от", name = "capitalizationFrom")
    private String capitalizationFrom;

    @Schema(description = "Капитализация до", name = "capitalizationUpTo")
    private String capitalizationUpTo;

    @Schema(description = "Средний объем торгов от", name = "volumeFrom")
    private String volumeFrom;

    @Schema(description = "Средний объем торгов до", name = "volumeUpTo")
    private String volumeUpTo;

}
