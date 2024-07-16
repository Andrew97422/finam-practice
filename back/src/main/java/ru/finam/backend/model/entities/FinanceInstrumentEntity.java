package ru.finam.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "finance_instruments")
public class FinanceInstrumentEntity {
    @Id
    @Column(name = "finance_instrument_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "firm_id")
    @JsonIgnore
    private FirmEntity firm;

    @ManyToOne
    @JoinColumn(name = "instrument_type_id")
    @JsonIgnore
    private InstrumentTypeEntity type;

    @Column(name = "average_trading_volume")
    private float average_trading_volume;

    @Column(name = "price")
    private float price;
}
