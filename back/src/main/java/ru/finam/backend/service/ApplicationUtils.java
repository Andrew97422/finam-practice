package ru.finam.backend.service;

import org.springframework.stereotype.Service;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.model.entities.FirmEntity;

@Service
public class ApplicationUtils {

    public FinanceInstrumentResponseDTO mapToFinanceInstrumentResponseDTO(FinanceInstrumentEntity fi){
        FirmEntity f = fi.getFirm();

        return  FinanceInstrumentResponseDTO.builder()
                .ticker(f.getTicker())
                .name(f.getName())
                .price(fi.getPrice())
                .averageTradingVolume(fi.getAverageTradingVolume())
                .capitalization(f.getCapitalization())
                .build();
    }
}
