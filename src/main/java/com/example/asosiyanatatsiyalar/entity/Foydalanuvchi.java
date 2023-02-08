package com.example.asosiyanatatsiyalar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Foydalanuvchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    private String ism;
    @Column(nullable = false)
    private String familiya;
    @Column(nullable = false)
    private String parsportRaqami;
    @Column(nullable = false)
    private String manzili;
    @OneToMany(mappedBy = "foydalanuvchi", cascade = CascadeType.ALL)
    List<HisobRaqam> hisobRaqamList;
}
