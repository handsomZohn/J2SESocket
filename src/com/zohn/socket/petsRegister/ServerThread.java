package com.zohn.socket.petsRegister;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhang on 2018/5/12.
 */
public class ServerThread extends Thread {
    // 和本线程相关的Socket
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    // 线程启动 响应客户端请求
    public void run() {

        try{
            // 获得输入流
            InputStream inputStream = socket.getInputStream();
            // 获得流 可以对对象进行反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            // 获得输出流
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            // 读取宠物信息
            Pet pet = (Pet)objectInputStream.readObject();
            System.out.println("----宠物注册信息----");
            System.out.println("宠物姓名:" + pet.getName());
            System.out.println("宠物性别:" + pet.getGender());
            System.out.println("宠物类型:" + pet.getType());

            // 给宠物主人一个响应
            String reply = "恭喜，您的宠物已经在本店注册成功！！";
            printWriter.write(reply);
            printWriter.flush();

            printWriter.close();
            outputStream.close();
            objectInputStream.close();
            inputStream.close();
            socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
