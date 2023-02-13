package com.example.asosiyanatatsiyalar.dto;

import lombok.Data;

import java.util.List;

@Data
public class FoydalanuvchiDto {
    private String ism,familiya,pasportRaqami,manzili,sana;
    private List<HisobRaqamDto> hisobRaqamDtoList;

}
