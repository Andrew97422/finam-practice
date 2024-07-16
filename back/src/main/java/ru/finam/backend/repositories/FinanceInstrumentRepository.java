package ru.finam.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;


@Repository
public interface FinanceInstrumentRepository extends JpaRepository<FinanceInstrumentEntity, Integer> {

}
