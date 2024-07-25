import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * ClassName: Client
 * Package: com.yovinchen.protobuf
 *
 * @author yovinchen
 * @since 2024/7/25 上午9:33
 */

public class Client {

    public static byte[] msg;

    static {
        VideoInfo.VideoFeature feature = VideoInfo.VideoFeature.newBuilder()
                .setAuthorGender(123)
                .setChannelId(321)
                .build();
        msg = feature.toByteArray();

//         msg = "测试字符串".getBytes();
//        msg = "{\"author_gender\":123,\"channel_id\":321}".getBytes();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动...");
        // 创建一个流套接字并将其连接到指定主机上的指定端口号
        Socket socket = new Socket("localhost", 8001);
        // 向服务器端发送数据
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.write(msg);
        out.close();
        socket.close();
    }
}
