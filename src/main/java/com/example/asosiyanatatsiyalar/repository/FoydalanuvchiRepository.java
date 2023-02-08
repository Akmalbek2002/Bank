package com.example.asosiyanatatsiyalar.repository;

import com.example.asosiyanatatsiyalar.entity.Foydalanuvchi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoydalanuvchiRepository extends JpaRepository<Foydalanuvchi,Integer> {
    boolean existsByParsportRaqami(String pasportRaqam);
}
