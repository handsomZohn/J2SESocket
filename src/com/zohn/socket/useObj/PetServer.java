package com.zohn.socket.useObj;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通过对象实现
 * Created by zhang on 2018/5/5.
 */
public class PetServer {
    public static void main(String[] args) {
        try{
            // 1.建立一个服务器Socket（ServerSocket）绑定指定端口并开始监听
            ServerSocket serverSocket = new ServerSocket(8800);
            // 2.使用accept（）方法阻塞等待监听，获得新的链接
            Socket socket = serverSocket.accept();
            // 3.获得输入流
            InputStream inputStream = socket.getInputStream();
            // 获得流，可以对对象进行反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            // 获得输出流
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            // 4.读取用户信息
            Pet pet = (Pet)objectInputStream.readObject();
            System.out.println("宠物注册信息:");
            System.out.println("宠物名字:" + pet.getName());
            System.out.println("宠物品种:" + pet.getType());
            System.out.println("宠物性别:" + pet.getGender());
            // 给宠物的主人一个响应
            String reply = "恭喜！！您的胖虎已在本店注册·····";
            printWriter.write(reply); // 可以变化的名字
            printWriter.flush();
            // 5.关闭资源
            printWriter.close();
            outputStream.close();
            objectInputStream.close();
            socket.close();
            serverSocket.close();
        } catch ( Exception e){
            e.printStackTrace();
        }
    }
}
