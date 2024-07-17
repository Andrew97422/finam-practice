package ru.finam.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.finam.backend.repositories.FinanceInstrumentRepository;
import ru.finam.backend.repositories.FirmRepository;
import ru.finam.backend.repositories.InstrumentTypeRepository;
import ru.finam.backend.repositories.SectorRepository;

@Service
@RequiredArgsConstructor
public class FirmFilterService {

    private FirmRepository firmRepository;
    private InstrumentTypeRepository instrumentTypeRepository;
    private SectorRepository sectorRepository;
    private FinanceInstrumentRepository financeInstrumentRepository;


}
