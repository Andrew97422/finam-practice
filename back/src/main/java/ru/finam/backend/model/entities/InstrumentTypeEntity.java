package ru.finam.backend.model.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @ToString.Exclude
    private List<FinanceInstrumentEntity> financeInstrumentList;
}