package com.miner.blockchainproject.service;

import com.miner.blockchainproject.model.Wallet;
import com.miner.blockchainproject.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepo;

    public Wallet createWallet() {
        Wallet wallet = new Wallet();
        wallet.setAddress(UUID.randomUUID().toString());
        return walletRepo.save(wallet);
    }
}
