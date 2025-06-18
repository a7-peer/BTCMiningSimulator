# Blockchain Simulator Project

## Overview

This project simulates a simplified **Blockchain** system using **Java Spring Boot**. It demonstrates fundamental blockchain concepts such as:

- Blocks and transactions
- Mining with proof-of-work
- Blockchain immutability and data integrity
- Transaction mempool and confirmation
- REST APIs for interaction

The project aims to provide hands-on understanding of blockchain mechanics and distributed system principles.

---

## Project Architecture

### 1. Model Layer

- **Block**: Represents a block in the blockchain containing:
  - Index
  - Timestamp
  - Previous block hash
  - Hash
  - Nonce (mining variable)
  - List of transactions (one-to-many relationship)
- **BlockchainTransaction**: Represents transactions, with a confirmation flag to indicate if included in a block.

### 2. Repository Layer

- **BlockRepository**: Manages persistence of blocks.
- **TransactionRepository**: Manages transactions, including retrieving unconfirmed transactions (mempool).

### 3. Service Layer

- **BlockchainService**: Contains core blockchain logic including:
  - Creating the genesis block
  - Mining new blocks using pending transactions
  - Maintaining chain integrity
  - Marking transactions confirmed upon mining

### 4. Controller Layer

- **BlockchainController**: REST API endpoints for:
  - Viewing the blockchain
  - Viewing pending transactions (mempool)
  - Creating new transactions
  - Mining blocks

### 5. Utility

- **HashUtil**: Provides SHA-256 hashing for block data to secure the blockchain.

---

## Running the Project

1. Clone the repository.
2. Configure database in `application.properties` (default is H2 in-memory database).
3. Run the Spring Boot application.
4. Use REST clients (Postman, curl) to interact:
   - `POST /transaction` to add transactions.
   - `GET /mempool` to view unconfirmed transactions.
   - `POST /mine` to mine a new block.
   - `GET /chain` to view the blockchain.

---

## In-Memory Database and Synchronization

### Using In-Memory Database (H2)

- The project uses **H2**, an in-memory relational database, to persist blockchain data and transactions during runtime.
- Data is stored fully in RAM, enabling very fast access without disk I/O delays.
- **Volatility**: Since data resides in memory, it is lost when the application stops unless snapshots or backups are implemented.

### Handling Synchronization and Concurrency

- Multiple API calls (threads) may access or modify blockchain data concurrently.
- To ensure thread safety and data consistency:
  - Spring Data JPA transactions guarantee atomicity of operations.
  - Database-level locking and isolation prevent race conditions during block mining and transaction confirmation.
  - The service layer ensures only one mining process can modify the blockchain at a time.
- The one-to-many relationship (`Block` → `Transactions`) uses **cascade operations** to atomically save blocks with their transactions.

### Considerations for Distributed Synchronization

- This project simulates a single-node blockchain.
- For real distributed blockchain networks:
  - Nodes must synchronize blocks and transactions via networking protocols.
  - Consensus algorithms (e.g., Proof-of-Work, Raft, Paxos) ensure agreement on blockchain state.
  - Forks are resolved via longest-chain rules or conflict resolution.
  - Replication and consistency models maintain synchronized in-memory data across nodes.

---

## Blockchain Concepts Demonstrated

- **Proof-of-Work Mining**: The miner iteratively changes the nonce until the hash starts with a set number of zeros (`"0000"`), simulating computational work.
- **Immutability**: Each block contains the hash of the previous block, making tampering easily detectable.
- **Mempool**: Unconfirmed transactions are collected and mined together into a block.
- **Genesis Block**: The first block, created automatically if no blocks exist.
- **Transaction Confirmation**: Transactions are marked confirmed only after being included in a mined block.

---

## Technologies Used

- Java 17+
- Spring Boot (REST API, Dependency Injection)
- Spring Data JPA with Hibernate
- H2 Database (in-memory)
- Lombok
- SHA-256 Hashing (`MessageDigest`)

---

## Future Work

- Implement multi-node distributed consensus and synchronization.
- Add digital signatures and wallet management.
- Introduce transaction fees and mining rewards.
- Build a UI for blockchain visualization.
- Add persistent storage options and backup mechanisms.

---

## Contact & License

For questions or contributions, please contact [Your Name] at [Your Email].

This project is open source under the MIT License.

---

