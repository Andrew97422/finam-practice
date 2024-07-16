package ru.finam.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.finam.backend.model.entities.FirmEntity;
import ru.finam.backend.model.entities.SectorEntity;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, Integer> {
    List<FirmEntity> getFirmsBySectorId(Integer id);
    List<FirmEntity> getFirmsBySector(String type);
}
