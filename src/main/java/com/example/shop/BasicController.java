package com.example.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class BasicController {
    @GetMapping("/")
    String hello() {
        return "index.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String about() {
        return "꺄륵";
    }

    @GetMapping("/mypage")
    @ResponseBody
    String mypage() {
        return "마이페이지입니다~";
    }

    //숙제
    LocalDate date = LocalDate.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    String dateFormatterNow = date.format(dateFormatter);

    LocalTime time = LocalTime.now();
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String timeFormatterNow = time.format(timeFormatter);

    @GetMapping("/date")
    @ResponseBody
    String showDate() {
        return "날짜 : " + dateFormatterNow + "   시간 : " + timeFormatterNow;
    }
}
