import socket
import hashlib
import random
def mod_exp(base, exp, mod):
    return pow(base, exp, mod)

def mod_inverse(a, m):
    return pow(a, -1, m)

def hash_msg(msg):
    return int(hashlib.sha256(msg.encode()).hexdigest(), 16)

p = 23      
q = 11        
g = 2         

x = random.randint(1, q-1)  
y = mod_exp(g, x, p)        

print("Public Key (p, q, g, y):", p, q, g, y)

server = socket.socket()
server.bind(('localhost', 9999))
server.listen(1)

print("Server waiting for connection...")
conn, addr = server.accept()
print("Connected with", addr)

msg = input("Enter message to sign: ")

k = random.randint(1, q-1)
r = mod_exp(g, k, p) % q
k_inv = mod_inverse(k, q)

h = hash_msg(msg)

s = (k_inv * (h + x*r)) % q

data = f"{msg}|{r}|{s}|{p}|{q}|{g}|{y}"
conn.send(data.encode())

print("\nSignature sent:")
print("r =", r, "s =", s)

conn.close()