import socket

import video_info_pb2


def parse(buf):
    try:
        video_feature = video_info_pb2.VideoFeature()
        video_feature.ParseFromString(buf)
        return video_feature
    except Exception:
        return "暂时不支持转换"


if __name__ == "__main__":
    print("Server is starting")
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind(('localhost', 8001))  # 配置soket，绑定IP地址和端口号
    sock.listen(5)  # 设置最大允许连接数
    while True:  # 循环轮询socket状态，等待访问
        connection, address = sock.accept()
        buf = connection.recv(1024)
        print(f"原始数据:{buf}")
        print(f"数据长度：{len(buf)}")

        print(parse(buf))
        connection.close()
