package ru.finam.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.repositories.FinanceInstrumentRepository;
import ru.finam.backend.repositories.FirmRepository;
import ru.finam.backend.repositories.InstrumentTypeRepository;
import ru.finam.backend.repositories.SectorRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceInstrumentService {

    private FirmRepository firmRepository;
    private InstrumentTypeRepository instrumentTypeRepository;
    private SectorRepository sectorRepository;
    private FinanceInstrumentRepository financeInstrumentRepository;

    private

    public Page<FinanceInstrumentResponseDTO> filterFirm(FinanceInstrumentRequestDTO financeInstrumentRequestDTO, int offset, int limit){

    }

    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsByFirmNameOrTicker(FinanceInstrumentRequestDTO financeInstrumentRequestDTO,int offset,int limit){
        var result = financeInstrumentRepository.findFinanceInstrumentsByInstrumentTypeName(financeInstrumentRequestDTO.getType(), PageRequest.of(offset,limit));
        return null;
    }



    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsByInstrumentTypeName(FinanceInstrumentRequestDTO financeInstrumentRequestDTO,int offset,int limit){
        var result = financeInstrumentRepository.findFinanceInstrumentsByInstrumentTypeName(financeInstrumentRequestDTO.getType(), PageRequest.of(offset,limit));

    }

    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsBySector(FinanceInstrumentRequestDTO financeInstrumentRequestDTO,int offset,int limit){
        var result = financeInstrumentRepository.findFinanceInstrumentsBySector(financeInstrumentRequestDTO.getSector(), PageRequest.of(offset,limit));

    }

    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsByPriceRange(FinanceInstrumentRequestDTO financeInstrumentRequestDTO,int offset,int limit){
        var result = financeInstrumentRepository.findFinanceInstrumentsByFirmTicker(financeInstrumentRequestDTO.getTickerName(), PageRequest.of(offset,limit));

    }

    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsByCapitalizationRange(FinanceInstrumentRequestDTO financeInstrumentRequestDTO,int offset,int limit){
        var result = financeInstrumentRepository.findFinanceInstrumentsByFirmTicker(financeInstrumentRequestDTO.getTickerName(),PageRequest.of(offset,limit));

    }

    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsByTradingVolumeRange(FinanceInstrumentRequestDTO financeInstrumentRequestDTO,int offset,int limit){
        var result = financeInstrumentRepository.findFinanceInstrumentsByFirmTicker(financeInstrumentRequestDTO.getTickerName(),PageRequest.of(offset,limit));

    }

}
