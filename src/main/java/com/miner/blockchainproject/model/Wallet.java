package com.miner.blockchainproject.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
}