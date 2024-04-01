package com.example.shop;

public class Age {
    private int age = 25;
    private String name = "이소진";

    public void 한살더하기() {
        age += 1;
    }

    public void 나이설정(int age) {
        if (age < 0 || age >= 100)
            return;
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
