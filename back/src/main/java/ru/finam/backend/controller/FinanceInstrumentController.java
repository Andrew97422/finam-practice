package ru.finam.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.service.FinanceInstrumentService;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/firms")
@Tag(
        name = "Контроллер скринера"
)
@Validated
@Slf4j
public class FinanceInstrumentController {

    private final FinanceInstrumentService financeInstrumentService;

    @Operation(
            summary = "Получение инструментов",
            description = "Получения инструментов по фильтрам"
    )
    @PostMapping("/finance_instruments/{offset}/{limit}")
    public ResponseEntity<Page<FinanceInstrumentResponseDTO>> getFinanceInstruments(
            @PathVariable @Parameter(description = "Смещение") @Min(0) int offset,
            @PathVariable @Parameter(description = "Количество записей") @Min(1) int limit,
            @Valid @RequestBody FinanceInstrumentRequestDTO filter
    ) {
        try {
            Page<FinanceInstrumentResponseDTO> response = financeInstrumentService.getFinanceInstruments(filter, offset, limit);
            log.info("Instrument {} was found", filter);
            return ResponseEntity.ok(response);
        } catch (IllegalAccessError e) {
            log.error("Instrument {} was not found", filter);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
