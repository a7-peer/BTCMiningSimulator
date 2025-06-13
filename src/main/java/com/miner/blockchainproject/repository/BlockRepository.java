package com.miner.blockchainproject.repository;

import com.miner.blockchainproject.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {}