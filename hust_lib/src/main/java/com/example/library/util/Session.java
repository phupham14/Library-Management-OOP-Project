package com.example.library.util;

public class Session {

    // Đối tượng Singleton của SessionManager
    private static Session instance;

    // Thuộc tính để lưu customerId
    private int personId;
    private int customerId;

    // Constructor riêng tư để ngăn tạo đối tượng từ bên ngoài
    private Session() {
    }

    // Phương thức để lấy instance duy nhất của SessionManager
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Getter và Setter cho personId
    public int getPersonId() {
        return personId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
