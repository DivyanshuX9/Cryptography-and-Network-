<!-- HEADER ANIMATION -->
<div align="center">

```
 +---------+                                          +---------+
 |         |   H e l l o   W o r l d                 |         |
 | SENDER  | ------>------>------>------>-----------> | RECEIVER|
 |         |         [ENCRYPTING...]                  |         |
 +---------+               |                          +---------+
                           v
              +------------+------------+
              |                         |
              |   C I P H E R   C O R E |
              |                         |
              |  RSA   AES   DES        |
              |  ElGamal   DSS          |
              |  MD5   SHA   HMAC       |
              |                         |
              +------------+------------+
                           |
                           v
 +---------+                                          +---------+
 |         |   7f3a9c...  [CIPHERTEXT]                |         |
 | SENDER  | <------<------<------<------<----------- | RECEIVER|
 |         |         [DECRYPTING...]                  |         |
 +---------+                                          +---------+
```

```
  plaintext                key                  ciphertext
     |                      |                       |
     v                      v                       v
  [HELLO]  -->  [XOR / FEISTEL / MOD-N]  -->  [7f3a9c2b]
     ^                                              |
     |______________ DECRYPT ______________________|
```

<!-- live key exchange simulation -->
```
  Alice                              Bob
    |                                 |
    |---- (g^a mod p) -------------> |   Diffie-Hellman
    |<--- (g^b mod p) --------------- |   Key Exchange
    |                                 |
    |==== shared secret: g^ab mod p ==|
    |                                 |
    |---- [ AES(msg, secret) ] -----> |   Encrypted Channel
    |<--- [ AES(reply, secret) ] ---- |
```

</div>

---

# Cryptography and Network Security  BCSE309L

Socket-based implementations of core cryptographic algorithms built as part of the BCSE309L course. Each module runs a client-server pair over localhost to demonstrate encryption, decryption, hashing, and digital signatures.

---

## Modules

### Classical Ciphers  `Basic Ciphers/`  (Java)

| Folder | Cipher | Technique |
|---|---|---|
| `Ceaser/` | Caesar | Shift cipher |
| `Hillcipher/` | Hill | Matrix multiplication mod 26 |
| `playfair/` | Playfair | 5x5 key square digraph substitution |
| `railfence/` | Rail Fence | Zigzag transposition |
| `rowcol/` | Row-Column | Columnar transposition |
| `vernam/` | Vernam | XOR one-time pad |
| `vignere/` | Vigenere | Polyalphabetic substitution |

### Modern Crypto  (Python)

| Folder | Algorithm | What it does |
|---|---|---|
| `AES/` | AES | Symmetric block cipher over socket |
| `DES/` | DES | Feistel-based symmetric encryption |
| `RSA/` | RSA | Public-key encryption using `pow(m, e, n)` |
| `RSA_Signatue/` | RSA Signature | Sign and verify messages with RSA |
| `DSS/` | DSS | Digital Signature Standard |
| `Elgamal/` | ElGamal | Discrete-log based public-key crypto |
| `MD5/` | MD5 | Hash + integrity check over socket |

---

## How to run

### Python modules

```bash
# Terminal 1
python server.py

# Terminal 2
python client.py
```

### Java modules

```bash
# Terminal 1
javac Server.java && java Server

# Terminal 2
javac Client.java && java Client
```

Run the server first, then the client. All connections use `localhost`.

---

## Course Material

Lecture slides are in `CNS FAT/` covering:

- Classical crypto, DES, AES, IDEA, RC4
- RSA, ElGamal, ECC, Diffie-Hellman
- MD5, SHA-512, HMAC
- Digital Signatures, X.509, Kerberos
- SSL/TLS, IPSec, PGP, S/MIME, Firewalls, IDS

---

## Stack

- Python 3 — `socket`, `hashlib`, `pycryptodome`
- Java — `java.net.Socket`, `java.io`

---

**Reg No:** 23BDS1155
