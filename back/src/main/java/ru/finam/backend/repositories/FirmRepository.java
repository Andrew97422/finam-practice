package ru.finam.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.model.entities.FirmEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface FirmRepository extends JpaRepository<FirmEntity, Integer> {
    Optional<FirmEntity> findFirmBySectorId(Integer id);
    Optional<FirmEntity> findFirmByName(String name);
    Optional<FirmEntity> findFirmByTicker(String ticker);

    List<FinanceInstrumentEntity> getFinanceInstrumentsByFirmId(Integer id);
    List<FinanceInstrumentEntity> getFinanceInstrumentsByFirmName(String name);
    List<FinanceInstrumentEntity> getFinanceInstrumentsByFirmTicker(String ticker);
}
