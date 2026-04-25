import socket
client=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
client.connect(('localhost',12345))

p=61
q=53

e=5
n=p*q
phi=(p-1)*(q-1)
def mode_inv(e,phi):
    for d in range(1,n):
        if (e*d)%n==1:
            return d
d=mode_inv(e,phi)
message=input("Enter message to be encrypted: ")

signature=[]
for ch in message:
    m=ord(ch)
    s=pow(m,d,n)
    signature.append(s)

client.send((message+"|"+",".join(signature)).encode())
client.close()
