package ru.finam.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.finam.backend.model.entities.FinanceInstrumentEntity;

import java.util.List;

@Repository
public interface FinanceInstrumentRepository extends JpaRepository<FinanceInstrumentEntity, Integer> {
    List<FinanceInstrumentEntity> findFinanceInstrumentsByInstrumentTypeId(Integer id);
    List<FinanceInstrumentEntity> findFinanceInstrumentsByInstrumentTypeName(String name);

    List<FinanceInstrumentEntity> findFinanceInstrumentsByFirmId(Integer id);
    List<FinanceInstrumentEntity> findFinanceInstrumentsByFirmTicker(String ticker);
    List<FinanceInstrumentEntity> findFinanceInstrumentsByFirmName(String name);

}