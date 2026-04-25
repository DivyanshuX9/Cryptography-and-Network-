import socket
import hashlib

def mod_exp(base, exp, mod):
    return pow(base, exp, mod)

def mod_inverse(a, m):
    return pow(a, -1, m)

def hash_msg(msg):
    return int(hashlib.sha256(msg.encode()).hexdigest(), 16)

client = socket.socket()
client.connect(('localhost', 9999))

data = client.recv(1024).decode()
msg, r, s, p, q, g, y = data.split("|")

r, s = int(r), int(s)
p, q, g, y = int(p), int(q), int(g), int(y)

print("Received Message:", msg)
print("Signature: r =", r, "s =", s)

h = hash_msg(msg)

w = mod_inverse(s, q)
u1 = (h * w) % q
u2 = (r * w) % q

v = ((mod_exp(g, u1, p) * mod_exp(y, u2, p)) % p) % q

print("\nComputed v =", v)

if v == r:
    print(" Signature VALID")
else:
    print("Signature INVALID")

client.close()