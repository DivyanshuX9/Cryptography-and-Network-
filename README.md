```
+--------+     plaintext      +----------+     ciphertext     +--------+
|        | -----------------> |          | -----------------> |        |
| SENDER |                    |  CIPHER  |                    |  RECV  |
|        | <----------------- |          | <----------------- |        |
+--------+     decrypted      +----------+       key          +--------+
     |                             |                               |
     |        [RSA / AES / DES]    |        [MD5 / SHA / HMAC]    |
     |        [ElGamal / DSS]      |        [Digital Signatures]  |
     +-----------------------------+-------------------------------+
```

# Cryptography and Network Security  BCSE309L

Socket-based implementations of core cryptographic algorithms, built as part of the BCSE309L course. Each module runs a client-server pair over localhost to demonstrate encryption, decryption, hashing, and digital signatures.

---

## Modules

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

Each module has a `server.py` (or `*Server.py`) and a `client.py` (or `*Client.py`).

```bash
# Terminal 1
python server.py

# Terminal 2
python client.py
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

- Python 3
- `socket`, `hashlib`, `Crypto` / `pycryptodome`

---

