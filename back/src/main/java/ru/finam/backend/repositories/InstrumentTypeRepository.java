package ru.finam.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.model.entities.InstrumentTypeEntity;

import java.util.List;

@Repository
public interface InstrumentTypeRepository extends JpaRepository<InstrumentTypeEntity, Integer> {
    List<FinanceInstrumentEntity> getFinanceInstrumentsByInstrumentTypeId(Integer id);
    List<FinanceInstrumentEntity> getFinanceInstrumentsByInstrumentType(String type);
}
