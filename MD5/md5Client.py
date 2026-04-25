import socket
import hashlib

client = socket.socket()
client.connect(('localhost', 5000))

message = input("Enter message: ")
hash_value = hashlib.md5(message.encode()).hexdigest()

print("Generated MD5:", hash_value)

data = message + "||" + hash_value

client.send(data.encode())

client.close()