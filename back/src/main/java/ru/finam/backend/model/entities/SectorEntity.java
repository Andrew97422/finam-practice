package ru.finam.backend.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sectors")
public class SectorEntity {
    @Id
    @Column(name = "sector_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_of_sector")
    private String typeOfSector;

    @OneToMany(mappedBy = "sector")
    private List<FirmEntity> firmList;
}
