package com.zohn.socket.petsRegister;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhang on 2018/5/11.
 */
public class PetClient {
    public static void main(String[] args) {
        try{
            // 1.建立socket客户端 指定服务的地址以及端口
            Socket socket = new Socket("127.0.0.1", 8800);
            // 2.得到Socket读写流
            OutputStream outputStream = socket.getOutputStream();
            // 对象序列化流
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // 输入流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            // 3.利用流按照一定的协议对Socket进行读写操作（创建宠物对象，将宠物信息发送到服务器）
            Pet pet = new Pet();
            pet.setName("胖虎虎");
            pet.setType("吉娃娃");
            pet.setGender("男");
            objectOutputStream.writeObject(pet);

            socket.shutdownOutput();

            // 接受服务器的响应并打印显示
            String reply = null;
            while (!((reply = bufferedReader.readLine()) == null)) {
                System.out.println("我是宠物主人，宠物店的响应为：" + reply );
            }
            //  4.关闭资源
            bufferedReader.close();
            inputStream.close();
            objectOutputStream.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
