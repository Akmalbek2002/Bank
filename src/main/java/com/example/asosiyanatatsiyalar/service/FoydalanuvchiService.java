package com.example.asosiyanatatsiyalar.service;

import com.example.asosiyanatatsiyalar.dto.ApiResponse;
import com.example.asosiyanatatsiyalar.dto.FoydalanuvchiDto;
import com.example.asosiyanatatsiyalar.dto.HisobRaqamDto;
import com.example.asosiyanatatsiyalar.entity.Foydalanuvchi;
import com.example.asosiyanatatsiyalar.entity.HisobRaqam;
import com.example.asosiyanatatsiyalar.repository.FoydalanuvchiRepository;
import com.example.asosiyanatatsiyalar.repository.HisobRaqamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoydalanuvchiService {

     @Autowired
    FoydalanuvchiRepository foydalanuvchiRepository;
     @Autowired
    HisobRaqamRepository hisobRaqamRepository;
    public ApiResponse addUser1(FoydalanuvchiDto foydalanuvchiDto) {
        boolean b = foydalanuvchiRepository.existsByParsportRaqami(foydalanuvchiDto.getPasportRaqami());
        if(b){
           return new ApiResponse("Siz allaqachon ro'yhatdan o'tdiz",false);
        }
        else{
            Foydalanuvchi foydalanuvchi=new Foydalanuvchi();
            foydalanuvchi.setIsm(foydalanuvchiDto.getIsm());
            foydalanuvchi.setFamiliya(foydalanuvchiDto.getFamiliya());
            foydalanuvchi.setParsportRaqami(foydalanuvchiDto.getPasportRaqami());
            foydalanuvchi.setManzili(foydalanuvchiDto.getManzili());
            List<HisobRaqam> hisobRaqamList=new ArrayList<>();
            for (HisobRaqamDto hisobRaqamDto : foydalanuvchiDto.getHisobRaqamDtoList()) {
                HisobRaqam hisobRaqam=new HisobRaqam();
                hisobRaqam.setKartaNomi(hisobRaqamDto.getKartaNomi());
                hisobRaqam.setKartaRaqami(hisobRaqamDto.getKartaRaqami());
                hisobRaqam.setAmalQilishMuddati(hisobRaqamDto.getAmalQilishMuddati());
                hisobRaqam.setBankNomi(hisobRaqamDto.getBankNomi());
                hisobRaqam.setFoydalanuvchi(foydalanuvchi);
                hisobRaqamList.add(hisobRaqam);
            }
            foydalanuvchi.setHisobRaqamList(hisobRaqamList);
            foydalanuvchiRepository.save(foydalanuvchi);
            return new ApiResponse("Muvaffaqiyatli o'tdingiz",true);
        }
    }
}
