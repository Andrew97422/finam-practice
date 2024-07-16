package ru.finam.backend.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instrument_types")
public class InstrumentTypeEntity {
    @Id
    @Column(name = "instrument_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "instrument_type")
    private String name;

    @OneToMany(mappedBy = "instrumentType")
    private List<FinanceInstrumentEntity> financeInstrumentList;
}