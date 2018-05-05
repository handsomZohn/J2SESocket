package com.zohn.socket.useObj;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 通过对象实现
 * Created by zhang on 2018/5/5.
 */
public class PetClient {
    public static void main(String[] args) {
        try {
            // 1.建立客户端socket链接，指定服务器的位置以及端口
            Socket socket = new Socket("127.0.0.1", 8800);

            // 2. 得到Socket的读写流
            OutputStream outputStream = socket.getOutputStream();
            // 对象序列化流
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            // 输入流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // 3.利用流按照一定的协议对Socket进行读写操作（创建宠物对象，将宠物信息发送到服务器）
            Pet pet = new Pet();
            pet.setName("胖虎");
            pet.setType("虎");
            pet.setGender("Woman");
            objectOutputStream.writeObject(pet); // writeObject

            socket.shutdownOutput();
            // 接受服务器的响应并打印显示
            String reply = null;
            while (!((reply = bufferedReader.readLine()) == null)){
                System.out.println("我是宠物的主人，宠物店的响应为:" + reply);
            }
            // 关闭资源
            bufferedReader.close();
            inputStream.close();
            objectOutputStream.close();
            outputStream.close();
            socket.close();


        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
