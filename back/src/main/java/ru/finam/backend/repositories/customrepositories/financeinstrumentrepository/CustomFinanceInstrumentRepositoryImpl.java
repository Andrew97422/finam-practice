package ru.finam.backend.repositories.customrepositories.financeinstrumentrepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.model.entities.FirmEntity;
import ru.finam.backend.model.entities.InstrumentTypeEntity;
import ru.finam.backend.model.entities.SectorEntity;

@Repository
public class CustomFinanceInstrumentRepositoryImpl implements CustomFinanceInstrumentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<FinanceInstrumentEntity> findFinanceInstrumentsByFilter(
        FinanceInstrumentRequestDTO dto) {

        // пришлось использовать такой вариант, потому что иначе возникает проблема с Alias
        // в запросе
        // простите за костыль)) зато теперь вместо 3 запросов hibernate выполняет только один

        String query = composeNativeQueryWithFilters(dto);
        List<Object[]> queryResults = getResultList(query);

        List<FinanceInstrumentEntity> entityList = new ArrayList<>();

        for(Object[] row : queryResults){
            InstrumentTypeEntity it = InstrumentTypeEntity.builder()
                .id((Integer)row[0])
                .name((String)row[1])
                .build();

            SectorEntity s = SectorEntity.builder()
                .id((Integer)row[2])
                .name((String)row[3])
                .build();

            FirmEntity f = FirmEntity.builder()
                .id((Integer)row[4])
                .name((String)row[5])
                .ticker((String)row[6])
                .capitalization((float)row[8])
                .sector(s)
                .build();

            FinanceInstrumentEntity fi = FinanceInstrumentEntity.builder()
                .id((Integer)row[9])
                .price((float)row[12])
                .averageTradingVolume((float)row[13])
                .instrumentType(it)
                .firm(f)
                .build();

            entityList.add(fi);
        }

        return entityList;
    }

    private String composeNativeQueryWithFilters(FinanceInstrumentRequestDTO dto){
        String filterQuery =
            "SELECT it.instrument_type_id AS IT_INSTRUMENT_TYPE_ID, it.instrument_type, "
                + "s.sector_id AS S_SECTOR_ID, s.type_of_sector, f.firm_id AS F_FIRM_ID, " // 4
                + "f.name, f.ticker, f.sector_id AS F_SECTOR_ID, f.capitalization, "
                + "fi.finance_instrument_id, fi.firm_id AS FI_FIRM_ID, fi.instrument_type_id " //9
                + "AS FI_INSTRUMENT_TYPE_ID, fi.price, "
                + "fi.average_trading_volume FROM finance_instruments fi "
                + "JOIN firms f ON f.firm_id = fi.firm_id "
                + "JOIN sectors s ON s.sector_id = f.sector_id "
                + "JOIN instrument_types it ON it.instrument_type_id = fi.instrument_type_id "
                + "WHERE ";

        if (!dto.getTickerName().isEmpty()) {
            filterQuery += " (f.ticker LIKE '%" + dto.getTickerName() + "%' OR "
                + " f.name LIKE '%" + dto.getTickerName() + "%') AND";
        }

        filterQuery += " (s.type_of_sector LIKE '" + dto.getSector() + "') AND";

        filterQuery += " (it.instrument_type LIKE '" + dto.getType() + "') AND";

        filterQuery += " (fi.price BETWEEN "
            + dto.getPriceFrom() + " AND " +
            dto.getPriceUpTo() + ") AND";

        filterQuery += " (f.capitalization BETWEEN "
            + dto.getCapitalizationFrom() + " AND " +
            dto.getCapitalizationUpTo() + ") AND";

        filterQuery += " (fi.average_trading_volume BETWEEN "
            + dto.getVolumeFrom() + " AND " +
            dto.getVolumeUpTo() + ")";

        return filterQuery;
    }

    private List<Object[]> getResultList(String nativeQuery){
        return em.createNativeQuery(nativeQuery).getResultList();
    }
}
