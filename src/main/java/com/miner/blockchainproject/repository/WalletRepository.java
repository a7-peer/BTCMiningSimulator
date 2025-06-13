package com.miner.blockchainproject.repository;

import com.miner.blockchainproject.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {}