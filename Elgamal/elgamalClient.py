import socket
import random

client = socket.socket()
client.connect(('localhost', 5000))
data = client.recv(1024).decode()
p, g, y = map(int, data.split(','))

print("Received Public Key:", p, g, y)

m = int(input("Enter numeric message (< p): "))

k = random.randint(1, p-2)


c1 = pow(g, k, p)
c2 = (m * pow(y, k, p)) % p

print("Encrypted values:", c1, c2)

client.send(f"{c1},{c2}".encode())

client.close()