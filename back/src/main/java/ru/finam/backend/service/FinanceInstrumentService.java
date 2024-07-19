package ru.finam.backend.service;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.repositories.FinanceInstrumentRepository;
import ru.finam.backend.validation.ValidationService;

@Service
@RequiredArgsConstructor
public class FinanceInstrumentService {

    private final FinanceInstrumentRepository financeInstrumentRepository;
    private final ApplicationUtils applicationUtils;
    private final ValidationService validationService;

    public Page<FinanceInstrumentResponseDTO> getFinanceInstruments(
            FinanceInstrumentRequestDTO filter, int offset, int limit) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        // проверка полей DTO на валидность
        validationService.checkRequestDTOFieldsAreValid(filter);

        // Получение списка отфильтрованных сущностей из БД
        List<FinanceInstrumentEntity> entitylist =
                financeInstrumentRepository.findFinanceInstrumentsByFilter(filter);

        // проверка offset и limit на валидность
        validationService.checkOffsetAndLimitAreValid(offset, limit, entitylist.size());

        // Преобразование списка сущностей в список responseDTO
        List<FinanceInstrumentResponseDTO> responseDTOList =
                applicationUtils.mapToFinanceInstrumentResponseDTOList(entitylist);

        // перевод в Page<FinanceInstrumentResponseDTO> и возвращение
        return applicationUtils.convertListToPage(responseDTOList, offset, limit);
    }

}