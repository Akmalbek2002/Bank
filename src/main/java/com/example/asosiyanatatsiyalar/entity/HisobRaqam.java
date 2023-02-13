package com.example.asosiyanatatsiyalar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class HisobRaqam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String kartaNomi;
    @Column(nullable = false)
    private String kartaRaqami;
    @Column(nullable = false)
    private String amalQilishMuddati;
    @Column(nullable = false)
    private String bankNomi;
    @Column(nullable = false)
    private double balans;
    @Column(nullable = false)
    private boolean holat;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    Foydalanuvchi foydalanuvchi;
}
