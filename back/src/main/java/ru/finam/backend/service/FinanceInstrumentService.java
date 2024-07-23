package ru.finam.backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;

import ru.finam.backend.validation.ValidationService;

@Service
@RequiredArgsConstructor
public class FinanceInstrumentService {

    private final ApplicationUtils applicationUtils;
    private final ValidationService validationService;

    @PersistenceContext
    private final EntityManager em;

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

    private List<FinanceInstrumentEntity> getFilteredInstrumentsFromDB(FinanceInstrumentRequestDTO filter){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FinanceInstrumentEntity> cr =
                cb.createQuery(FinanceInstrumentEntity.class);
        Root<FinanceInstrumentEntity> root =
                cr.from(FinanceInstrumentEntity.class);

        root.fetch("firm").fetch("sector");
        root.fetch("instrumentType");

        List<Predicate> predicates = new ArrayList<>();

        if (!filter.getTickerName().isEmpty()) {
            predicates.add(cb.or(
                    cb.like(root.get("firm").get("ticker"), "%" + filter.getTickerName() + "%"),
                    cb.like(root.get("firm").get("name"), "%" + filter.getTickerName() + "%")
            ));
        }

        predicates.add(cb.like(root.get("firm").get("sector").get("name"), filter.getSector()));
        predicates.add(cb.like(root.get("instrumentType").get("name"), filter.getType()));

        predicates.add(cb.between(root.get("price"),
                filter.getPriceFrom(), filter.getPriceUpTo()));
        predicates.add(cb.between(root.get("firm").get("capitalization"),
                filter.getCapitalizationFrom(), filter.getCapitalizationUpTo()));
        predicates.add(cb.between(root.get("averageTradingVolume"),
                filter.getVolumeFrom(), filter.getVolumeUpTo()));

        // сортировка тут

        if(!filter.getSortOrder().isEmpty()) {
            switch (filter.getSortBy()) {
                case "price":
                    cr.orderBy(
                            filter.getSortOrder().equals("asc") ? cb.asc(root.get("price")) :
                                    cb.desc(root.get("price"))
                    );
                    break;
                case "name":
                    cr.orderBy(
                            filter.getSortOrder().equals("asc") ? cb.asc(root.get("firm").get("name")) :
                                    cb.desc(root.get("firm").get("name"))
                    );
                    break;
                case "ticker":
                    cr.orderBy(
                            filter.getSortOrder().equals("asc") ? cb.asc(root.get("firm").get("ticker")) :
                                    cb.desc(root.get("firm").get("ticker"))
                    );
                    break;
                case "capitalization":
                    cr.orderBy(
                            filter.getSortOrder().equals("asc") ? cb.asc(root.get("firm").get("capitalization")) :
                                    cb.desc(root.get("firm").get("capitalization"))
                    );
                    break;
                case "averageTradingVolume":
                    cr.orderBy(
                            filter.getSortOrder().equals("asc") ? cb.asc(root.get("averageTradingVolume")) :
                                    cb.desc(root.get("averageTradingVolume"))
                    );
                    break;
            }
        }
        cr.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

        return em.createQuery(cr).getResultList();
    }

}