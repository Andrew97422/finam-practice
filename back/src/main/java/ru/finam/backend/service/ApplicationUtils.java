package ru.finam.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.finam.backend.model.dto.FinanceInstrumentResponseDTO;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.model.entities.FirmEntity;

import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class ApplicationUtils {

    public FinanceInstrumentResponseDTO mapToFinanceInstrumentResponseDTO(FinanceInstrumentEntity fi){
        FirmEntity f = fi.getFirm();

        return  FinanceInstrumentResponseDTO.builder()
                .ticker(f.getTicker())
                .name(f.getName())
                .price(fi.getPrice())
                .averageTradingVolume(fi.getAverageTradingVolume())
                .capitalization(f.getCapitalization())
                .build();
    }

    public List<FinanceInstrumentResponseDTO> mapToFinanceInstrumentResponseDTOList(List<FinanceInstrumentEntity> l){
       return l.parallelStream()
               .map(this::mapToFinanceInstrumentResponseDTO)
               .toList();
    }

    public Page<FinanceInstrumentResponseDTO> convertListToPage(List<FinanceInstrumentResponseDTO> list,
                                                                 int offset, int limit){
        Pageable pageRequest = createPageRequestUsing(offset, limit);

        int start = (int) pageRequest.getOffset();
        int end = start + pageRequest.getPageSize();

        List<FinanceInstrumentResponseDTO> pageContent = list.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, list.size());
    }

    public Pageable createPageRequestUsing(int offset, int limit){
        return PageRequest.of(offset, limit);
    }

    public boolean isInRange(double x, double fromInclusive, double toInclusive){
        return Double.compare(x, fromInclusive) >= 0 &&  Double.compare(toInclusive, x) >= 0;
    }
}
