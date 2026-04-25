import socket
import hashlib

server = socket.socket()
server.bind(('localhost', 5000))
server.listen(1)

print("MD5 Server waiting for connection...")

conn, addr = server.accept()
print("Connected by:", addr)

data = conn.recv(1024).decode()

message, received_hash = data.split("||")

calculated_hash = hashlib.md5(message.encode()).hexdigest()

print("Received Message:", message)
print("Received Hash:", received_hash)
print("Calculated Hash:", calculated_hash)

if received_hash == calculated_hash:
    print("Message Integrity Verified")
else:
    print("Message Tampered!")

conn.close()
server.close()