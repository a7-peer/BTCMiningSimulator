package com.miner.blockchainproject.repository;

import com.miner.blockchainproject.model.BlockchainTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<BlockchainTransaction, Long> {
    List<BlockchainTransaction> findByConfirmedFalse();
}