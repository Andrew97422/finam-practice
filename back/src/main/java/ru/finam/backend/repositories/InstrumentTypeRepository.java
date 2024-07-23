package ru.finam.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.finam.backend.model.entities.InstrumentTypeEntity;

import java.util.Optional;

@Repository
public interface InstrumentTypeRepository extends JpaRepository<InstrumentTypeEntity, Integer> {
    Optional<InstrumentTypeEntity> findByName(String name);
}
