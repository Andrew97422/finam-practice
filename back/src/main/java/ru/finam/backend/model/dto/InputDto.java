package ru.finam.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputDto {
    private String tickername;
    private String type;
    private String sector;
    private float priceFrom;
    private float priceUpTo;
    private float capitalizationFrom;
    private float capitalizationUpTo;
    private float volumeFrom;
    private float volumeUpTo;
}
