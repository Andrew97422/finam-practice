package ru.finam.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Slf4j
public class FinanceInstrumentController {

    private final FinanceInstrumentService financeInstrumentService;

    @Operation(
            summary = "Получение инструментов",
            description = "Получения инструментов по фильтрам"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Данной страницы не существует",
                    content = @Content(mediaType = "application/json", examples = { @ExampleObject( value = "{\"name\": \"pong\", \"description\":\"test\"}") })),
            @ApiResponse(responseCode = "400", description = "Неправильное значение числа элементов страницы",
                    content = @Content(mediaType = "application/json", examples = { @ExampleObject( value = "{\"name\": \"pong\", \"description\":\"test\"}") })),
            @ApiResponse(responseCode = "200", description = "ОК",
                    content = @Content(mediaType = "application/json", examples = { @ExampleObject( value = "{\"name\": \"pong\", \"description\":\"test\"}") }))
    })
    @PostMapping("/finance_instruments/{offset}/{limit}")
    public ResponseEntity<Page<FinanceInstrumentResponseDTO>> getFinanceInstruments(
            @Valid @RequestBody FinanceInstrumentRequestDTO filter,

            @PathVariable @Parameter(description = "Смещение")
            @Min(0) @Max(Integer.MAX_VALUE)
            @RequestParam(required = true, defaultValue = "0", name = "offset") int offset,

            @PathVariable @Parameter(description = "Количество записей")
            @Min(0) @Max(100)
            @RequestParam(required = true, defaultValue = "0", name = "limit") int limit

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
