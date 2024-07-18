package ru.finam.backend.repositories.custominstrumentrepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;

@Repository
public class CustomFinanceInstrumentRepositoryImpl implements CustomFinanceInstrumentRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<FinanceInstrumentEntity> findFinanceInstruments(String query,
      FinanceInstrumentRequestDTO dto) {
    // проверка непустых полей
    // сформировать один запрос
    String baseQuery = "select * from finance_instruments fi join firms on f.firm_id = fi.firm_di"
        + " join sectors s on s.sector_id = f.sector_id join instrument_types it on"
        + " it.instrument_type_id = fi.instrument_type_id;";

    if (!dto.getTickerName().isEmpty()) {
      baseQuery = baseQuery.concat(" WHERE f.ticker LIKE %" + dto.getTickerName() + "% or "
          + " f.name LIKE %" + dto.getTickerName() + "%;");
    } else if (!dto.getSector().isEmpty()) {
      baseQuery = baseQuery.concat(" AND ....");
    }

    //
    // финальный запрос
    //
    return entityManager.createQuery(query, FinanceInstrumentEntity.class).getResultList();
  }
}
