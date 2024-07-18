package ru.finam.backend.repositories.custominstrumentrepository;

import java.util.List;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;

public interface CustomFinanceInstrumentRepository {
      List<FinanceInstrumentEntity> findFinanceInstruments(String query,
          FinanceInstrumentRequestDTO dto);
}
