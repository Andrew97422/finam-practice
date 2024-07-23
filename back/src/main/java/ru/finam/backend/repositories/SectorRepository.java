package ru.finam.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.finam.backend.model.entities.SectorEntity;

import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, Integer> {
    Optional<SectorEntity> findByName(String name);
}