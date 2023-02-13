package com.example.asosiyanatatsiyalar.service;

import com.example.asosiyanatatsiyalar.dto.ApiResponse;
import com.example.asosiyanatatsiyalar.dto.BalansToldirish;
import com.example.asosiyanatatsiyalar.entity.HisobRaqam;
import com.example.asosiyanatatsiyalar.repository.HisobRaqamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.NoTransactionException;

import java.util.Optional;

@Service
public class HisobRaqamService {
    @Autowired
    HisobRaqamRepository hisobRaqamRepository;
    public ApiResponse BalansToldirish(BalansToldirish balansToldirish) {
        Optional<HisobRaqam> byKartaRaqami = hisobRaqamRepository.findByKartaRaqami(balansToldirish.getKartaRaqam());
        if(byKartaRaqami.isPresent()){
            HisobRaqam hisobRaqam = byKartaRaqami.get();
            hisobRaqam.setBalans(balansToldirish.getBalans());
            hisobRaqamRepository.save(hisobRaqam);
            return new ApiResponse("Hisobingiz to'ldirildi",true);
        }
        return new ApiResponse("Bunday hisob raqam mavjud emas",false);
    }

    public ApiResponse Otkazma(BalansToldirish balansToldirish, String kartaRaqam) {
        Optional<HisobRaqam> byKartaRaqami = hisobRaqamRepository.findByKartaRaqami(kartaRaqam);
        if(byKartaRaqami.isPresent()){
        if(byKartaRaqami.get().getBalans()>= balansToldirish.getBalans()){
            Optional<HisobRaqam> byKartaRaqami1 = hisobRaqamRepository.findByKartaRaqami(balansToldirish.getKartaRaqam());
            if(byKartaRaqami1.isPresent()){
                HisobRaqam hisobRaqam = byKartaRaqami.get();
                hisobRaqam.setBalans(hisobRaqam.getBalans()- balansToldirish.getBalans());
                hisobRaqamRepository.save(hisobRaqam);
                HisobRaqam hisobRaqam1 = byKartaRaqami1.get();
                if(hisobRaqam1.isHolat()) {
                    hisobRaqam1.setBalans(hisobRaqam.getBalans() + balansToldirish.getBalans());
                    hisobRaqamRepository.save(hisobRaqam1);
                    return new ApiResponse("O'tkazma muvaffaqiyatli o'tkazildi", true);
                }
                throw new NoTransactionException("O'tkazilmadi");
            }
            return new ApiResponse("Bunday karta raqam mavjud emas",false);
        }
        return new ApiResponse("Balans yetarli emas",false);
    }
        return new ApiResponse("Bunday karta raqam mavjud emas",false);
    }
}
