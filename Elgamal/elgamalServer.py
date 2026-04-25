import socket
import random

def mod_inverse(a, p):
    for i in range(1, p):
        if (a * i) % p == 1:
            return i
    return None


p = 23
g = 5
x = 6   

y = pow(g, x, p)  

print("Public Key (p,g,y):", p, g, y)
print("Private Key x:", x)


server = socket.socket()
server.bind(('localhost', 5000))
server.listen(1)

print("Server waiting for connection...")

conn, addr = server.accept()
print("Connected by:", addr)

conn.send(f"{p},{g},{y}".encode())

data = conn.recv(1024).decode()
c1, c2 = map(int, data.split(','))

s = pow(c1, x, p)
s_inv = mod_inverse(s, p)

m = (c2 * s_inv) % p

print("Decrypted message:", m)

conn.close()
server.close()