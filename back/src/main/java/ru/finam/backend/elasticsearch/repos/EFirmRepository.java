package ru.finam.backend.elasticsearch.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.finam.backend.elasticsearch.entity.FirmEntity;

public interface EFirmRepository extends ElasticsearchRepository<FirmEntity, Integer> {
}
