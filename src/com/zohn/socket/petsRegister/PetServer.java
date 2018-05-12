package com.zohn.socket.petsRegister;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhang on 2018/5/12.
 */
public class PetServer {
    public static void main(String[] args) throws Exception{
        // 1.建立serverSocket服务 绑定指定端口 【开始监听】
        ServerSocket serverSocket = new ServerSocket(8800);
        // 2.使用accept方法阻塞等待监听 获得新的连接
        Socket socket = null;
        // 记录注册宠物的数量
        int num = 0;
        // 一直处于监听状态
        while (true) {
            socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket);
            serverThread.run();
            num ++;
            System.out.println("有" + num + "只宠物在本店注册！");
        }
    }
}
