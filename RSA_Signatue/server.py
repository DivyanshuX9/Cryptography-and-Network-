import socket
p=61
q=53
n=p*q
phi=(p-1)*(q-1)
e=5

def mode_iv(e,phi):
    for d in range(1,n):
        if (e*d)%n==1:
            return d

d=mode_iv(e,phi)

server=socket.socket(socket.AF_INET,socket.SOCK_STREAM) 
server.bind(('localhost',12345))    
server.listen(1)

conn,add=server.accept()

data=conn.recv(1024).decode().split("|")
message=data[0]
signature=list(map(int,data[1].split(",")))
verify=""
for s in signature:
    m=pow(s,e,n)
    verify+=m

if(message==verify):
    print("Verified")
