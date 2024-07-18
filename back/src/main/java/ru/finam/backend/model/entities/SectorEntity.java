package ru.finam.backend.model.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "sectors")
public class SectorEntity {
    @Id
    @Column(name = "sector_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_of_sector")
    private String name;

    @OneToMany(mappedBy = "sector")
    @ToString.Exclude
    private List<FirmEntity> firmList;
}
