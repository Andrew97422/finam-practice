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
  public List<FinanceInstrumentEntity> findFinanceInstruments(FinanceInstrumentRequestDTO dto) {
    // проверка непустых полей
    // сформировать один запрос

    String baseQuery = "SELECT * FROM finance_instruments fi JOIN firms f ON f.firm_id = fi.firm_id"
        + " JOIN sectors s ON s.sector_id = f.sector_id JOIN instrument_types it ON"
        + " it.instrument_type_id = fi.instrument_type_id WHERE";

    if (!dto.getTickerName().isEmpty() ) {
      baseQuery = baseQuery.concat(" f.ticker LIKE %" + dto.getTickerName() + "% OR "
          + " f.name LIKE %" + dto.getTickerName() + "% AND");
    }
    if (!dto.getSector().isEmpty()) {
      baseQuery = baseQuery.concat(" s.type_of_sector LIKE %" + dto.getSector() + "% AND");
    }
    if (!dto.getType().isEmpty()) {
      baseQuery = baseQuery.concat(" it.instrument_type LIKE %" + dto.getType() + "% AND");
    }
    if (!dto.getPriceFrom().isEmpty() && !dto.getPriceUpTo().isEmpty()) {
      baseQuery = baseQuery.concat(" fi.price BETWEEN %"
              + dto.getPriceFrom() + "% AND %" + dto.getPriceUpTo() + "% AND");
    }
    if (!dto.getCapitalizationFrom().isEmpty() && !dto.getCapitalizationUpTo().isEmpty()) {
      baseQuery = baseQuery.concat(" f.capitalization BETWEEN %"
              + dto.getCapitalizationFrom() + "% AND %" + dto.getCapitalizationUpTo() + "% AND");
    }
    if (!dto.getVolumeFrom().isEmpty() && !dto.getVolumeUpTo().isEmpty()) {
      baseQuery = baseQuery.concat(" fi.average_trading_volume BETWEEN %"
              + dto.getVolumeFrom() + "% AND %" + dto.getVolumeUpTo() + "% AND");
    }
    baseQuery += " TRUE";

    return entityManager.createQuery(baseQuery, FinanceInstrumentEntity.class).getResultList();
  }
}
