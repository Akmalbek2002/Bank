package com.example.asosiyanatatsiyalar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
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
    private LocalDate tugulganSana;
    @Transient
    private Integer ismUzunligi;

    @Transient
    private String ismBoshHarf;
    @Transient
    private Integer yosh;
    @OneToMany(mappedBy = "foydalanuvchi", cascade = CascadeType.ALL)
    List<HisobRaqam> hisobRaqamList;
    public Integer getIsmUzunligi() {
        return ism.length()!=0?ism.length():null;
    }


    public String getIsmBoshHarf() {
        String s=ism.substring(0,1),f=familiya.substring(0,1);
        return  s+f;
    }

    public Integer getYosh() {
        return Period.between(tugulganSana, LocalDate.now()).getYears();
    }
}
