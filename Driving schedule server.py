import socket
from datetime import datetime

IP = '192.168.14.82'
PORT = 6789
SOCKET_TIMEOUT = 100000


def send_the_info(client_socket, info):
    now = datetime.now()
    day = now.strftime("%a")
    # print("day:", day)
    hour = now.strftime("%H")
    # print("hour:", hour)
    if day == "Saturday" and hour == "00":
        lastweekinfo = info
        info = ""
    if info == "":
        info = "no"
    print("send")
    print(info)
    b = bytes(info, 'ascii')
    client_socket.send(b)


def save_client_info(client_request):
    client_request = client_request[6::]
    info = client_request
    info = info.decode("ascii")
    print("save")
    print(info)
    return info


def socket_handle(server_socket, info):
    server_socket.listen(10)
    print("Listening for connections on port %d" % PORT)
    while True:
        client_socket, client_address = server_socket.accept()
        # print('New connection received')
        client_socket.settimeout(SOCKET_TIMEOUT)
        print('Client connected')
        client_request = client_socket.recv(1024)
        if client_request[2:6] == b'POST':
            print('Want to save his information')
            info = save_client_info(client_request)
        elif client_request[2:5] == b'GET':
            print('Want updated information')
            send_the_info(client_socket, info)
        else:
            print('Error: Not a valid request')
        client_socket.close()
        socket_handle(server_socket, info)


def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((IP, PORT))
    info = ""
    socket_handle(server_socket, info)
    main()


if __name__ == "__main__":
    main()
