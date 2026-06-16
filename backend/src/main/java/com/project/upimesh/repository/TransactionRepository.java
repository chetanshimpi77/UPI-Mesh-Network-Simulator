package com.project.upimesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.upimesh.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop20ByOrderByIdDesc();
    boolean existsByPacketHash(String packetHash);
}
