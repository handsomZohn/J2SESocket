package com.zohn.socket.useStr;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 宠物店用户咨询[字符串方式实现]
 * 服务器端
 * Created by zhang on 2018/5/5.
 */
public class PetServer {
    public static void main(String[] args) throws IOException {

        try{

            // 1.建立一个服务器Socket（ServerSocket）绑定指定端口并开始监听
            ServerSocket serverSocket = new ServerSocket(8800);
            // 2.使用accept（）方法阻塞等待监听，获得新的链接
            Socket socket = serverSocket.accept();
            // 3.获得输入流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // 获得输出流
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            // 4.读取用户信息
            String info = null;
            while (!((info = bufferedReader.readLine()) == null)) {
                System.out.println("这里是猪哥服务中心，胖虎发送的消息是:" + info);
            }

            // 给胖虎一个响应【响应客户端】
            String reply = "全世界最可爱的宋明潞；";
            printWriter.print(reply);
            printWriter.flush();

            // 5.关闭资源
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
