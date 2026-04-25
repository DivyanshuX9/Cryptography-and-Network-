import socket
from Crypto.Util.Padding import pad
from Crypto.Cipher import DES

key=b'8bytekey'

client=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
client.connect(('localhost',12345))


message=input()
cipher=DES.new(key,DES.MODE_ECB)

encrypted_message=cipher.encrypt(pad(message.encode(),DES.block_size))
encode=encrypted_message.encode()

client.send(encode)
client.close()
