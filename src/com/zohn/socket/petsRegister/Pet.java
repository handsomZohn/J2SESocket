package com.zohn.socket.petsRegister;

import java.io.Serializable;

/**
 * 宠物类
 * Created by zhang on 2018/5/5.
 */
public class Pet implements Serializable {
    // 名字
    private String name;
    // 品种
    private String type;
    // 性别
    private String gender;

    public Pet(String name, String type, String gender) {
        this.name = name;
        this.type = type;
        this.gender = gender;
    }

    public Pet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
