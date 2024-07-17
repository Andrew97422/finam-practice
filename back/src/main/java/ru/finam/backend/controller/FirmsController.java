package ru.finam.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.finam.backend.service.FirmFilterService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/firms")
public class FirmsController {

    private FirmFilterService firmFilterService;

    @GetMapping
    public List<InputDto> findAllInputDto() {
        return null;
    }

    @PostMapping("save_input_dto")
    public InputDto saveInputDto(@RequestBody InputDto inputdto) {
        return null;
    }

    @GetMapping("/{tiker}")
    public InputDto findByTiker(@PathVariable String tiker) {
        return null;
    }

    @PutMapping("update_input_dto")
    public InputDto updateInputDto(InputDto inputdto) {
        return null;
    }

    @DeleteMapping("delete_input_dto/{tiker}")
    public void deleteInputDto(@PathVariable String tiker) {

    }
}
