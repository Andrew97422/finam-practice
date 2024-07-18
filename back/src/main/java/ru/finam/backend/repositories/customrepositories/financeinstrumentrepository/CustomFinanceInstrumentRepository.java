package ru.finam.backend.repositories.customrepositories.financeinstrumentrepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;

public interface CustomFinanceInstrumentRepository {

        List<FinanceInstrumentEntity> findFinanceInstrumentsByFilter(
             FinanceInstrumentRequestDTO filter);
}
