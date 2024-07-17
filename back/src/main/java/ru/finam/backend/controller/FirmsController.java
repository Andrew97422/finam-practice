package ru.finam.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.service.ApplicationUtils;
import ru.finam.backend.service.FinanceInstrumentService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/firms")
public class FirmsController {

    private final FinanceInstrumentService financeInstrumentService;
    private final ApplicationUtils applicationUtils;

    @GetMapping("/")
    public String financeInstrumentRequestDTO(Model model) {
        return "financeInstrumentRequestDTO";
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
