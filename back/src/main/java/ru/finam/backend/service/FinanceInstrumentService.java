package ru.finam.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.finam.backend.model.dto.FinanceInstrumentRequestDTO;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.repositories.FinanceInstrumentRepository;
import ru.finam.backend.repositories.FirmRepository;
import ru.finam.backend.repositories.InstrumentTypeRepository;
import ru.finam.backend.repositories.SectorRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceInstrumentService {

    private final FinanceInstrumentRepository financeInstrumentRepository;
    private final ApplicationUtils applicationUtils;

    // Получить List<FinanceInstrumentEntity> (либо сразу все данные, либо если есть tickerName, то только те,
    // которые удовлетворяют условию)

    // Отфильтровать List<FinanceInstrumentEntity> по всем параметрам, если нужно

    // Пройтись по каждому элементу List<FinanceInstrumentEntity> и применить map-функцию.
    // Получить List<FinanceInstrumentResponseDTO>

    // Перевести этот список в Page<FinanceInstrumentResponseDTO> и вернуть контроллеру

    public Page<FinanceInstrumentResponseDTO> getFinanceInstruments(FinanceInstrumentRequestDTO filter,
                                                         int offset, int limit){

        List<FinanceInstrumentEntity> buf;

        String tickerName = filter.getTickerName();
        String type = filter.getType();
        String sector = filter.getSector();
        String priceFrom = filter.getPriceFrom();
        String priceUpTo = filter.getPriceUpTo();
        String capitalizationFrom = filter.getCapitalizationFrom();
        String capitalizationUpTo = filter.getCapitalizationUpTo();
        String volumeFrom = filter.getVolumeFrom();
        String volumeUpTo = filter.getVolumeUpTo();

        List<FinanceInstrumentEntity> fiEntityList = tickerName.isEmpty() ? financeInstrumentRepository.findAll() :
                financeInstrumentRepository.findFinanceInstrumentsByNameOrTicker(tickerName, tickerName);

        if(!type.isEmpty())
            buf = fiEntityList.parallelStream()
                    .filter( fiEntity -> fiEntity.getInstrumentType().getName().equals(type))
                    .toList();

        else if(!sector.isEmpty())
            buf = fiEntityList.parallelStream()
                    .filter( fiEntity -> fiEntity.getFirm().getSector().getName().equals(sector))
                    .toList();

        // фильтрация по остальным параметрам

        // return  Page<FinanceInstrumentResponseDTO>
    }

    private List<FinanceInstrumentEntity> findFinanceInstrumentsByFirmNameOrTicker(FinanceInstrumentRequestDTO filter,
                                                                                   int offset, int limit){
        return financeInstrumentRepository.findFinanceInstrumentsByInstrumentTypeName(filter.getTickerName());
    }

    private Page<FinanceInstrumentResponseDTO> convertListToPage(List<FinanceInstrumentResponseDTO> list,
                                                                 int offset, int limit){
        Pageable pageRequest = createPageRequestUsing(offset, limit);

        int start = (int) pageRequest.getOffset();
        int end = start + pageRequest.getPageSize();

        List<FinanceInstrumentResponseDTO> pageContent = list.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, list.size());
    }

    private Pageable createPageRequestUsing(int offset, int limit){
        return PageRequest.of(offset, limit);
    }


//    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsByInstrumentTypeName(FinanceInstrumentRequestDTO filter,
//                                                                                          int offset, int limit){
//        var result = financeInstrumentRepository.findFinanceInstrumentsByInstrumentTypeName(filter.getType(),
//                PageRequest.of(offset,limit));
//        return null;
//    }
//
//    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsBySector(FinanceInstrumentRequestDTO filter,
//                                                                              int offset,int limit){
//        var result = financeInstrumentRepository.findFinanceInstrumentsBySector(filter.getSector(),
//                PageRequest.of(offset,limit));
//        return null;
//    }
//
//    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsByPriceRange(FinanceInstrumentRequestDTO filter,
//                                                                                  int offset,int limit){
//        var result = financeInstrumentRepository.findFinanceInstrumentsByFirmTicker(filter.getTickerName(),
//                PageRequest.of(offset,limit));
//        return null;
//    }
//
//    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsByCapitalizationRange(FinanceInstrumentRequestDTO fiRequestDTO,
//                                                                                           int offset,int limit){
//        var result = financeInstrumentRepository.findFinanceInstrumentsByFirmTicker(fiRequestDTO.getTickerName(),
//                PageRequest.of(offset,limit));
//        return null;
//    }
//
//    private Page<FinanceInstrumentResponseDTO> findFinanceInstrumentsByTradingVolumeRange(FinanceInstrumentRequestDTO filter,
//                                                                                          int offset,int limit){
//        var result = financeInstrumentRepository.findFinanceInstrumentsByFirmTicker(filter.getTickerName(),
//                PageRequest.of(offset,limit));
//        return null;
//    }

}
