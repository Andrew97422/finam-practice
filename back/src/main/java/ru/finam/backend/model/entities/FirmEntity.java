package ru.finam.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private List<FinanceInstrumentEntity> financeInstruments;
}
