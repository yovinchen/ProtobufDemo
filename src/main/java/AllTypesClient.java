import com.google.protobuf.ByteString;

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
public class AllTypesClient {

    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动...");

        // 创建一个 AllTypes 消息实例
        AllTypesOuterClass.AllTypes.Builder builder = AllTypesOuterClass.AllTypes.newBuilder();
        builder.setBoolField(true);
        builder.setStringField("测试字符串");
        builder.setBytesField(ByteString.copyFromUtf8("字节流"));
        builder.setInt32Field(123);
        builder.setInt64Field(123L);
        builder.setUint32Field(456);
        builder.setUint64Field(456L);
        builder.setSint32Field(-123);
        builder.setSint64Field(-123L);
        builder.setFixed32Field(123);
        builder.setFixed64Field(123L);
        builder.setSfixed32Field(-123);
        builder.setSfixed64Field(-123L);
        builder.setFloatField(123.45f);
        builder.setDoubleField(123.45);
        builder.addRepeatedInt32Field(1);
        builder.addRepeatedInt32Field(2);
        builder.putMapInt32StringField(1, "value1");
        builder.putMapInt32StringField(2, "value2");
        builder.setEnumField(AllTypesOuterClass.AllTypes.EnumType.ENUM_VALUE_1);
        builder.setNestedMessageField(AllTypesOuterClass.AllTypes.MessageType.newBuilder()
                .setNestedStringField("嵌套字符串")
                .build());

        // 构建消息
        AllTypesOuterClass.AllTypes allTypesMsg = builder.build();

        // 创建一个流套接字并将其连接到指定主机上的指定端口号
        Socket socket = new Socket("localhost", 8001);

        // 向服务器端发送数据
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.write(allTypesMsg.toByteArray());

        out.close();
        socket.close();
    }
}
