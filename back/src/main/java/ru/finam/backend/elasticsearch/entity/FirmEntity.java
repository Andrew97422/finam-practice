package ru.finam.backend.elasticsearch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
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
    @Column(name = "firm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "ticker")
    private String ticker;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private SectorEntity sector;

    @Column(name = "capitalization")
    private float capitalization;

    @OneToMany(mappedBy = "firm")
    @ToString.Exclude
    private List<FinanceInstrumentEntity> financeInstruments;
}
