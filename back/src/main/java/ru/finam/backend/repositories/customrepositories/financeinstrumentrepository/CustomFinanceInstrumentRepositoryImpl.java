package ru.finam.backend.repositories.customrepositories.financeinstrumentrepository;

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
    public List<FinanceInstrumentEntity> findFinanceInstrumentsByFilter(
        FinanceInstrumentRequestDTO dto) {

        String baseQuery =
            "FROM finance_instruments fi JOIN firms f ON f.firm_id = fi.firm_id"
                + " JOIN sectors s ON s.sector_id = f.sector_id JOIN instrument_types it ON"
                + " it.instrument_type_id = fi.instrument_type_id WHERE";

        if (!dto.getTickerName().isEmpty()) {
            baseQuery += " f.ticker LIKE '%" + dto.getTickerName() + "%' OR "
                + " f.name LIKE '%" + dto.getTickerName() + "%' AND";
        }

        baseQuery += " s.type_of_sector LIKE '" + dto.getSector() + "' AND";

        baseQuery += " it.instrument_type LIKE '" + dto.getType() + "' AND";

        baseQuery += " fi.price BETWEEN "
            + dto.getPriceFrom() + " AND " +
            dto.getPriceUpTo() + " AND";

        baseQuery += " f.capitalization BETWEEN "
            + dto.getCapitalizationFrom() + " AND " +
            dto.getCapitalizationUpTo() + " AND";

        baseQuery += " fi.average_trading_volume BETWEEN "
            + dto.getVolumeFrom() + " AND " +
            dto.getVolumeUpTo();

        return entityManager.createQuery(baseQuery, FinanceInstrumentEntity.class).getResultList();
    }
}
