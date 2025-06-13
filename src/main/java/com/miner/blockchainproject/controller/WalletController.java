package com.miner.blockchainproject.controller;


import com.miner.blockchainproject.model.Wallet;
import com.miner.blockchainproject.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @PostMapping("/create")
    public Wallet createWallet() {
        return walletService.createWallet();
    }
}