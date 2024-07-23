package ru.finam.backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;

import ru.finam.backend.repositories.FinanceInstrumentRepository;
import ru.finam.backend.validation.ValidationService;

@Service
@RequiredArgsConstructor
public class FinanceInstrumentService {

    private final ApplicationUtils applicationUtils;
    private final ValidationService validationService;
    private final FinanceInstrumentRepository fiRepository;

    @PersistenceContext

    public Page<FinanceInstrumentResponseDTO> getFinanceInstruments(
            FinanceInstrumentRequestDTO filter, int offset, int limit) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        // проверка полей DTO на валидность
        validationService.checkRequestDTOFieldsAreValid(filter);

        // Получение отфильтрованных данных с помощью CriteriaQuery
        List<FinanceInstrumentEntity> entitylist = getFilteredInstrumentsFromDB(filter);





        // проверка offset и limit на валидность
        validationService.checkOffsetAndLimitAreValid(offset, limit, entitylist.size());

        // Преобразование списка сущностей в список responseDTO
        List<FinanceInstrumentResponseDTO> responseDTOList =
                applicationUtils.mapToFinanceInstrumentResponseDTOList(entitylist);

        // перевод в Page<FinanceInstrumentResponseDTO> и возвращение
        return applicationUtils.convertListToPage(responseDTOList, offset, limit);
    }

    private List<FinanceInstrumentEntity> getFilteredInstruments(FinanceInstrumentRequestDTO filter){
       if(!filter.getTickerName().isEmpty()){
           //А тикернейм в кеше??

       }
    }

}