package ru.finam.backend.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.finam.backend.model.entities.FinanceInstrumentEntity;

import java.util.List;
import ru.finam.backend.repositories.customfirepository.CustomFinanceInstrumentRepository;

@Repository
public interface FinanceInstrumentRepository extends
    JpaRepository<FinanceInstrumentEntity, Integer>,
    CustomFinanceInstrumentRepository {

  List<FinanceInstrumentEntity> findFinanceInstrumentsByInstrumentTypeId(Integer id);

  List<FinanceInstrumentEntity> findFinanceInstrumentsByInstrumentTypeName(String name);

  List<FinanceInstrumentEntity> findFinanceInstrumentsByFirmId(Integer id);

  List<FinanceInstrumentEntity> findFinanceInstrumentsByFirmTicker(String ticker);

  List<FinanceInstrumentEntity> findFinanceInstrumentsByFirmName(String name);

  @Query(
      value = "SELECT fi.* FROM finance_instruments fi JOIN firms f ON fi.firm_id = f.firm_id" +
          " WHERE f.ticker LIKE %:ticker% OR f.name LIKE %:name%",
      nativeQuery = true
  )
  List<FinanceInstrumentEntity> findFinanceInstrumentsByNameOrTicker(@Param("ticker") String ticker,
      @Param("name") String name);

  @Query(
      value = "SELECT fi.* FROM finance_instruments fi JOIN firms f ON fi.firm_id = f.firm_id" +
          " JOIN sectors s ON f.sector_id = s.sector_id WHERE s.type_of_sector LIKE :sector",
      nativeQuery = true
  )
  List<FinanceInstrumentEntity> findFinanceInstrumentsBySector(@Param("sector") String sector);

  @Query(
      value = "SELECT fi.* FROM finance_instruments fi WHERE fi.price BETWEEN :priceFrom AND :priceUpTo",
      nativeQuery = true
  )
  List<FinanceInstrumentEntity> findFinanceInstrumentsByPriceRange(
      @Param("priceFrom") float priceFrom, @Param("priceUpTo") float priceUpTo
  );

  @Query(
      value = "SELECT fi.* FROM finance_instruments fi JOIN firms f ON fi.firm_id = f.firm_id" +
          " WHERE f.capitalization BETWEEN :capitalizationFrom AND :capitalizationUpTo",
      nativeQuery = true
  )
  List<FinanceInstrumentEntity> findFinanceInstrumentsByCapitalizationRange(
      @Param("capitalizationFrom") float capitalizationFrom,
      @Param("capitalizationUpTo") float capitalizationUpTo
  );

  @Query(
      value = "SELECT fi.* FROM finance_instruments fi WHERE fi.average_trading_volume " +
          "BETWEEN :volumeFrom AND :volumeUpTo",
      nativeQuery = true
  )
  List<FinanceInstrumentEntity> findFinanceInstrumentsByTradingVolumeRange(
      @Param("volumeFrom") float volumeFrom,
      @Param("volumeUpTo") float volumeUpTo
  );

}