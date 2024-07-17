package ru.finam.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.finam.backend.model.entities.FinanceInstrumentEntity;

import java.util.List;

@Repository
public interface FinanceInstrumentRepository extends JpaRepository<FinanceInstrumentEntity, Integer> {
    Page<FinanceInstrumentEntity> findFinanceInstrumentsByInstrumentTypeId(Integer id,Pageable pageable);
    Page<FinanceInstrumentEntity> findFinanceInstrumentsByInstrumentTypeName(String name,Pageable pageable);

    Page<FinanceInstrumentEntity> findFinanceInstrumentsByFirmId(Integer id,Pageable pageable);
    Page<FinanceInstrumentEntity> findFinanceInstrumentsByFirmTicker(String ticker,Pageable pageable);
    Page<FinanceInstrumentEntity> findFinanceInstrumentsByFirmName(String name,Pageable pageable);

    @Query(
            value = "SELECT fi.* FROM finance_instruments fi JOIN firms f ON fi.firms_id = f.firm_id" +
                    " WHERE f.ticker LIKE %:ticker% OR f.name LIKE %:name%",
            nativeQuery = true
    )
    Page<FinanceInstrumentEntity> findFinanceInstrumentsByNameOrTicker(@Param("ticker") String ticker,@Param("name") String name, Pageable pageable);

    @Query(
            value = "SELECT fi.* FROM finance_instruments fi JOIN firms f ON fi.firm_id = f.firm_id" +
            " JOIN sectors s ON f.sector_id = s.sector_id WHERE s.type_of_sector LIKE :sector",
            nativeQuery = true
    )
    Page<FinanceInstrumentEntity> findFinanceInstrumentsBySector(@Param("sector") String sector, Pageable pageable);

    @Query(
            value = "SELECT fi.* FROM finance_instruments fi WHERE fi.price BETWEEN :priceFrom AND :priceUpTo",
            nativeQuery = true
    )
    Page<FinanceInstrumentEntity> findFinanceInstrumentsByPriceRange(
            @Param("priceFrom") float priceFrom, @Param("priceUpTo") float priceUpTo,
            Pageable pageable
    );

    @Query(
            value = "SELECT fi.* FROM finance_instruments fi JOIN firms f ON fi.firms_id = f.firm_id" +
            " WHERE f.capitalization BETWEEN :capitalizationFrom AND :capitalizationUpTo",
            nativeQuery = true
    )
    Page<FinanceInstrumentEntity> findFinanceInstrumentsByCapitalizationRange(
            @Param("capitalizationFrom") float capitalizationFrom,
            @Param("capitalizationUpTo") float capitalizationUpTo,
            Pageable pageable
    );

    @Query(
            value = "SELECT fi.* FROM finance_instruments fi WHERE fi.average_trading_volume BETWEEN :volumeFrom AND :volumeUpTo",
            nativeQuery = true
    )
    Page<FinanceInstrumentEntity> findFinanceInstrumentsByTradingVolumeRange(
            @Param("volumeFrom") float volumeFrom,
            @Param("volumeUpTo") float volumeUpTo,
            Pageable pageable
    );

}