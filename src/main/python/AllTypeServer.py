import socket

import AllTypes_pb2


def parse(buf):
    try:
        all_types_msg = AllTypes_pb2.AllTypes()  # 创建 AllTypes 消息实例
        all_types_msg.ParseFromString(buf)  # 从字节流中解析消息
        return all_types_msg  # 返回解析后的消息实例
    except Exception as e:
        print(f"Error parsing message: {e}")
        return None  # 如果解析失败，返回 None 或者自定义的错误信息


if __name__ == "__main__":
    print("Server is starting")
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind(('localhost', 8001))
    sock.listen(5)

    while True:
        connection, address = sock.accept()
        buf = connection.recv(1024)
        print(f"原始数据: {buf}")
        print(f"数据长度：{len(buf)}")

        parsed_msg = parse(buf)
        if parsed_msg is not None:
            print(parsed_msg)  # 输出解析后的消息
        else:
            print("无法解析消息")

        connection.close()
