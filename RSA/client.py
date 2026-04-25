import socket

client=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
client.connect(('localhost',12345))

e,n=client.recv(1024).decode().split(" ");
message=input("Enter message to be encrypted: ")

encrypt=[]
for ch in message:
    m=ord(ch)
    c=pow(m,int(e),int(n))
    encrypt.append(c)

client.send(' '.join(encrypt).encode())
client.close()