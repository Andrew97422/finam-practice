package ru.finam.backend.repositories.customrepositories.financeinstrumentrepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;

@Repository
public class CustomFinanceInstrumentRepositoryImpl implements CustomFinanceInstrumentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<FinanceInstrumentEntity> findFinanceInstrumentsByFilter(
        FinanceInstrumentRequestDTO dto) {
        String filterQuery =
            "SELECT * FROM finance_instruments fi JOIN firms f ON f.firm_id = fi.firm_id"
                + " JOIN sectors s ON s.sector_id = f.sector_id JOIN instrument_types it ON"
                + " it.instrument_type_id = fi.instrument_type_id WHERE";

        if (!dto.getTickerName().isEmpty()) {
            filterQuery += " f.ticker LIKE '%" + dto.getTickerName() + "%' OR "
                + " f.name LIKE '%" + dto.getTickerName() + "%' AND";
        }

        filterQuery += " s.type_of_sector LIKE '" + dto.getSector() + "' AND";

        filterQuery += " it.instrument_type LIKE '" + dto.getType() + "' AND";

        filterQuery += " fi.price BETWEEN "
            + dto.getPriceFrom() + " AND " +
            dto.getPriceUpTo() + " AND";

        filterQuery += " f.capitalization BETWEEN "
            + dto.getCapitalizationFrom() + " AND " +
            dto.getCapitalizationUpTo() + " AND";

        filterQuery += " fi.average_trading_volume BETWEEN "
            + dto.getVolumeFrom() + " AND " +
            dto.getVolumeUpTo();

        return em.createNativeQuery(filterQuery, FinanceInstrumentEntity.class).getResultList();
    }
}
