package ru.finam.backend.model.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "firms")
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
