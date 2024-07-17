package ru.finam.backend.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.service.ApplicationUtils;
import ru.finam.backend.service.FinanceInstrumentService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/firms")
public class FirmsController {

    private static final Logger log = LoggerFactory.getLogger(FirmsController.class);
    private final FinanceInstrumentService financeInstrumentService;

    @GetMapping("/finance_instruments")
    public ResponseEntity<Page<FinanceInstrumentResponseDTO>> getFinanceInstruments(
            @PathVariable FinanceInstrumentRequestDTO filter,
            @PathVariable int offset,
            @PathVariable int limit

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

    @PostMapping("save_input_dto")
    public FinanceInstrumentRequestDTO saveInputDto(@RequestBody FinanceInstrumentRequestDTO inputdto) {
        return null;
    }

    @GetMapping("/{tiker}")
    public FinanceInstrumentRequestDTO findByTiker(@PathVariable String tiker) {
        return null;
    }

    @PutMapping("update_input_dto")
    public FinanceInstrumentRequestDTO updateInputDto(FinanceInstrumentRequestDTO inputdto) {
        return null;
    }

    @DeleteMapping("delete_input_dto/{tiker}")
    public void deleteInputDto(@PathVariable String tiker) {

    }
}
