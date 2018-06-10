package com.zohn.socket.useStr;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 宠物店用户咨询[字符串方式实现]
 * 客户端
 * Created by zhang on 2018/5/5.
 */
public class PetClient {
    public static void main(String[] args) {
        try{
            // 1.建立客户端Socket连接，指定服务器的位置以及端口
            Socket socket = new Socket("localhost", 8800    );
            // 2.得到socket的读写流
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            // 输入流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // 3.利用流按照一定的协议对Socket进行读、写操作
            String info = "宋明潞是我家最可爱的虎妹子，咦嘻嘻;猪哥你说是不是啊？？咦嘻嘻";
            printWriter.write(info);
            printWriter.flush();
            socket.shutdownOutput();
            // 接受服务器的响应，并打印显示信息
            String reply = null;
            while (!((reply = bufferedReader.readLine()) == null)) {
                System.out.println("胖虎威武！！" + reply);
            }
            // 4.关闭资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
