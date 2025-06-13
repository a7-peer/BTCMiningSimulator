package com.miner.blockchainproject.controller;

import com.miner.blockchainproject.model.Block;
import com.miner.blockchainproject.model.BlockchainTransaction;
import com.miner.blockchainproject.repository.TransactionRepository;
import com.miner.blockchainproject.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlockchainController {

    @Autowired
    private BlockchainService blockchainService;

    @Autowired
    private TransactionRepository transactionRepo;

    @GetMapping("/chain")
    public List<Block> getBlockchain() {
        return blockchainService.getAllBlocks();
    }

    @GetMapping("/mempool")
    public List<BlockchainTransaction> getMempoolTransactions() {
        return transactionRepo.findByConfirmedFalse();
    }

    @PostMapping("/transaction")
    public BlockchainTransaction createTransaction(@RequestBody BlockchainTransaction tx) {
        tx.setConfirmed(false);
        return transactionRepo.save(tx);
    }

    @PostMapping("/mine")
    public Block mineBlock() {
        List<BlockchainTransaction> mempool = transactionRepo.findByConfirmedFalse();
        if (mempool.isEmpty()) {
            throw new RuntimeException("No transactions to mine");
        }
        return blockchainService.mineBlock(mempool);
    }
}
