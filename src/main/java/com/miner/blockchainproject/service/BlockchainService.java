package com.miner.blockchainproject.service;

import com.miner.blockchainproject.model.Block;
import com.miner.blockchainproject.model.BlockchainTransaction;
import com.miner.blockchainproject.repository.BlockRepository;
import com.miner.blockchainproject.repository.TransactionRepository;
import com.miner.blockchainproject.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockchainService {

    @Autowired
    private BlockRepository blockRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    public Block createGenesisBlock() {
        Block block = new Block();
        block.setIndex(0);
        block.setTimestamp(System.currentTimeMillis());
        block.setTransactions(List.of());
        block.setPreviousHash("0");
        block.setNonce(0);
        block.setHash(HashUtil.calculateHash(block));
        return blockRepo.save(block);
    }

    public List<Block> getAllBlocks() {
        return blockRepo.findAll();
    }

    public Block getLastBlock() {
        List<Block> chain = blockRepo.findAll();
        return chain.isEmpty() ? createGenesisBlock() : chain.get(chain.size() - 1);
    }

    public Block mineBlock(List<BlockchainTransaction> mempool) {
        if (mempool.isEmpty()) {
            throw new IllegalArgumentException("No transactions to mine");
        }

        Block lastBlock = getLastBlock();
        Block newBlock = new Block();
        newBlock.setIndex(lastBlock.getIndex() + 1);
        newBlock.setTimestamp(System.currentTimeMillis());
        newBlock.setPreviousHash(lastBlock.getHash());
        newBlock.setTransactions(mempool);

        int nonce = 0;
        String hash;
        do {
            newBlock.setNonce(nonce++);
            hash = HashUtil.calculateHash(newBlock);
        } while (!hash.startsWith("0000"));

        newBlock.setHash(hash);

        // Mark transactions as confirmed
        for (BlockchainTransaction tx : mempool) {
            tx.setConfirmed(true);
            transactionRepo.save(tx);
        }

        return blockRepo.save(newBlock);
    }
}
