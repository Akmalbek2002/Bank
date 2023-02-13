package com.example.asosiyanatatsiyalar.controller;

import com.example.asosiyanatatsiyalar.dto.ApiResponse;
import com.example.asosiyanatatsiyalar.dto.BalansToldirish;
import com.example.asosiyanatatsiyalar.service.HisobRaqamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class HisobRaqamController {
    @Autowired
    HisobRaqamService hisobRaqamService;
    @PutMapping("/balans")
    public HttpEntity<?> BalansToldirish(@RequestBody BalansToldirish balansToldirish){
        ApiResponse apiResponse=hisobRaqamService.BalansToldirish(balansToldirish);
        return ResponseEntity.status(apiResponse.isHolat()?200:404).body(apiResponse.getXabar());
    }
    @Transactional
    @PutMapping("/otkazma/{kartaRaqam}")
    public HttpEntity<?> Otkazma(@RequestBody BalansToldirish balansToldirish, @PathVariable String kartaRaqam){
        ApiResponse apiResponse=hisobRaqamService.Otkazma(balansToldirish,kartaRaqam);
        return ResponseEntity.status(apiResponse.isHolat()?200:404).body(apiResponse.getXabar());
    }
}
