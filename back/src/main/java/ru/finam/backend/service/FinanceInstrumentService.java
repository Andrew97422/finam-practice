package ru.finam.backend.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.repositories.FinanceInstrumentRepository;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class FinanceInstrumentService {

  private final FinanceInstrumentRepository financeInstrumentRepository;
  private final ApplicationUtils applicationUtils;


  public Page<FinanceInstrumentResponseDTO> getFinanceInstruments(
      FinanceInstrumentRequestDTO filter, int offset, int limit) {

    List<FinanceInstrumentEntity> entitylist;

    // получаю с БД либо все данные, либо те, которые с именем или тикером tickerName
    entitylist = filterInstrumentsByNameOrTicker(filter.getTickerName());

    // фильтрация по типу инструмента
    entitylist = filterInstrumentsByInstrumentType(filter.getType(), entitylist);

    // фильтрация по сектору
    entitylist = filterInstrumentsBySectorType(filter.getSector(), entitylist);

    // по цене
    entitylist = filterInstrumentsByPriceRange(filter.getPriceFrom(), filter.getPriceUpTo(),
        entitylist);

    // по капитализации
    entitylist = filterInstrumentsByCapitalizationRange(filter.getCapitalizationFrom(),
        filter.getCapitalizationUpTo(), entitylist);

    // по объему
    entitylist = filterInstrumentsByAverageTradingVolumeRange(filter.getVolumeFrom(),
        filter.getVolumeUpTo(), entitylist);

    // перевод в List<FinanceInstrumentResponseDTO>, а затем в Page<FinanceInstrumentResponseDTO>
    return applicationUtils.convertListToPage(
        applicationUtils.mapToFinanceInstrumentResponseDTOList(entitylist), offset, limit);
  }

  private List<FinanceInstrumentEntity> filterInstruments(
      Predicate<FinanceInstrumentEntity> predicate, List<FinanceInstrumentEntity> l) {
    return l.parallelStream().filter(predicate).toList();
  }

  private List<FinanceInstrumentEntity> filterInstrumentsByNameOrTicker(String tickerName) {
    return tickerName.isEmpty() ? financeInstrumentRepository.findAll()
        : financeInstrumentRepository.findFinanceInstrumentsByNameOrTicker(tickerName, tickerName);
  }


  private List<FinanceInstrumentEntity> filterInstrumentsBySectorType(String type,
      List<FinanceInstrumentEntity> l) {
    return !type.isEmpty() ? filterInstruments(
        fiEntity -> fiEntity.getFirm().getSector().getName().equals(type), l) : l;
  }

  private List<FinanceInstrumentEntity> filterInstrumentsByInstrumentType(String type,
      List<FinanceInstrumentEntity> l) {
    return !type.isEmpty() ? filterInstruments(
        fiEntity -> fiEntity.getInstrumentType().getName().equals(type), l) : l;
  }

  private List<FinanceInstrumentEntity> filterInstrumentsByPriceRange(String from, String upTo,
      List<FinanceInstrumentEntity> l) {
    if (!from.isEmpty() && !upTo.isEmpty()) {
      return filterInstruments(
          fiEntity -> applicationUtils.isInRange(fiEntity.getPrice(), Float.parseFloat(from),
              Float.parseFloat(upTo)), l);
    } else {
      return l;
    }
  }

  private List<FinanceInstrumentEntity> filterInstrumentsByCapitalizationRange(String from,
      String upTo, List<FinanceInstrumentEntity> l) {

    if (!from.isEmpty() && !upTo.isEmpty()) {
      return filterInstruments(
          fiEntity -> applicationUtils.isInRange(fiEntity.getFirm().getCapitalization(),
              Float.parseFloat(from), Float.parseFloat(upTo)), l);
    } else {
      return l;
    }
  }

  private List<FinanceInstrumentEntity> filterInstrumentsByAverageTradingVolumeRange(String from,
      String upTo, List<FinanceInstrumentEntity> l) {
    if (!from.isEmpty() && !upTo.isEmpty()) {
      return filterInstruments(
          fiEntity -> applicationUtils.isInRange(fiEntity.getAverageTradingVolume(),
              Float.parseFloat(from), Float.parseFloat(upTo)), l);
    } else {
      return l;
    }
  }

  private List<FinanceInstrumentEntity> findFinanceInstrumentsByFirmNameOrTicker(
      FinanceInstrumentRequestDTO filter) {
    return financeInstrumentRepository.findFinanceInstrumentsByInstrumentTypeName(
        filter.getTickerName());
  }

  private List<FinanceInstrumentEntity> findFinanceInstrumentsByInstrumentTypeName(
      FinanceInstrumentRequestDTO filter) {
    return financeInstrumentRepository.findFinanceInstrumentsByInstrumentTypeName(
        filter.getType());
  }

  private List<FinanceInstrumentEntity> findFinanceInstrumentsBySector(
      FinanceInstrumentRequestDTO filter) {
    return financeInstrumentRepository.findFinanceInstrumentsBySector(filter.getSector());
  }

  private List<FinanceInstrumentEntity> findFinanceInstrumentsByPriceRange(
      FinanceInstrumentRequestDTO filter) {
    return financeInstrumentRepository.findFinanceInstrumentsByPriceRange(
        Float.parseFloat(filter.getPriceFrom()), Float.parseFloat(filter.getPriceUpTo()));
  }

  private List<FinanceInstrumentEntity> findFinanceInstrumentsByCapitalizationRange(
      FinanceInstrumentRequestDTO filter) {
    return financeInstrumentRepository.findFinanceInstrumentsByCapitalizationRange(
        Float.parseFloat(filter.getCapitalizationFrom()),
        Float.parseFloat(filter.getCapitalizationUpTo()));
  }

  private List<FinanceInstrumentEntity> findFinanceInstrumentsByTradingVolumeRange(
      FinanceInstrumentRequestDTO filter) {
    return financeInstrumentRepository.findFinanceInstrumentsByTradingVolumeRange(
        Float.parseFloat(filter.getVolumeFrom()), Float.parseFloat(filter.getVolumeUpTo()));
  }

}