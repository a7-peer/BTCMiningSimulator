package com.miner.blockchainproject.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class BlockchainTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromAddress;
    private String toAddress;
    private double amount;
    private boolean confirmed = false;
}
