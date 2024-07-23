package ru.finam.backend.elasticsearch.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.finam.backend.elasticsearch.entity.FirmEntity;

import java.util.List;

public interface EFirmRepository extends ElasticsearchRepository<FirmEntity, Integer> {
    List<FirmEntity> findByName(String name);
    List<FirmEntity> findByTicker(String ticker);
}
