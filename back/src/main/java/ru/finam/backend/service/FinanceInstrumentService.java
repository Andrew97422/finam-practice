package ru.finam.backend.service;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.repositories.FinanceInstrumentRepository;

@Service
@RequiredArgsConstructor
public class FinanceInstrumentService {

    private final FinanceInstrumentRepository financeInstrumentRepository;
    private final ApplicationUtils  applicationUtils;

    public Page<FinanceInstrumentResponseDTO> getFinanceInstruments(
        FinanceInstrumentRequestDTO filter, int offset, int limit) {

        // Получение списка отфильтрованных сущностей из БД
        List<FinanceInstrumentEntity> entitylist =
            financeInstrumentRepository.findFinanceInstrumentsByFilter(filter);

        // Преобразование списка сущностей в список responseDTO
        List<FinanceInstrumentResponseDTO> responseDTOList =
            applicationUtils.mapToFinanceInstrumentResponseDTOList(entitylist);

        // перевод в Page<FinanceInstrumentResponseDTO> и возвращение
        return applicationUtils.convertListToPage(responseDTOList, offset, limit);
    }
}