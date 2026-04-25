import socket
from Crypto.Cipher import DES
from Crypto.Util.Padding import unpad

key=b'8bytekey'

server=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
server.bind(('localhost',12345))
server.listen(1)

conn,addr=server.accept()

data=conn.recv(1024)

cipher=DES.new(key,DES.MODE_ECB)
decrypted_message=unpad(cipher.decrypt(data),DES.block_size)

print("Decrypted message:",decrypted_message.decode())