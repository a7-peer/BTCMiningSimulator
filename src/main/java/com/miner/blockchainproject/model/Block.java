package com.miner.blockchainproject.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int index;
    private long timestamp;
    private String previousHash;
    private String hash;
    private int nonce;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BlockchainTransaction> transactions;
}