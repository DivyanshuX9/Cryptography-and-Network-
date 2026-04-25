import socket
def mod_inverse(e, phi):
    for d in range(1, phi):
        if (e * d) % phi == 1:
            return d
p=7
q=5
n=p*q
phi=(p-1)(q-1)

e=3
d=mod_inverse(e, phi)
server=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
server.bind(('localhost',12345))
server.listen(1)

conn,addr=server.accept()

conn.send(f"{e} {n}".encode())

data=conn.recv(1024).decode().split(",")

decrypt=[]
for ch in data:
    c=ord(ch)
    m=pow(c,d,n)
    decrypt.append(m)

print(decrypt)