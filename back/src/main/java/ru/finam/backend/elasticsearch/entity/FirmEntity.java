package ru.finam.backend.elasticsearch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import ru.finam.backend.model.entities.FinanceInstrumentEntity;
import ru.finam.backend.model.entities.SectorEntity;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "firms_elk")
public class FirmEntity {
    @Id
    //@Field(name = "firm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Field(name = "name")
    private String name;

    @Field(name = "ticker")
    private String ticker;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private SectorEntity sector;

    @Field(name = "capitalization")
    private float capitalization;

    @OneToMany(mappedBy = "firm")
    @ToString.Exclude
    private List<FinanceInstrumentEntity> financeInstruments;
}
