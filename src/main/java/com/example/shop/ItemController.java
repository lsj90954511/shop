package com.example.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);

        var a = new Item();
        System.out.println(a.toString());

        //숙제
        /*var object = new Age();
        object.한살더하기();
        System.out.println(object.getAge());
        object.나이설정(12);
        System.out.println(object.getAge());*/

        return "list.html";
    }

    @GetMapping("/write")
    String write(Model model) {
        return "write.html";
    }

    /*@PostMapping("/add")
    String addPost(@RequestParam Map formData){
        var newItem = new Item();
        System.out.println(formData);
        newItem.setTitle((String) formData.get("title"));
        newItem.setPrice(Integer.parseInt((String)formData.get("price")));

        if (newItem.getTitle().length() <= 50)
            itemRepository.save(newItem);

        return "redirect:/list";
    }*/

    @PostMapping("/add")
    String addPost(@ModelAttribute Item item){

        if (item.getTitle().length() <= 50)
            itemRepository.save(item);

        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(Model model, @PathVariable Integer id) {
        Optional<Item> result = itemRepository.findById(Long.valueOf(id));

        if(result.isPresent()) {
            Item item = result.get();
            model.addAttribute("detail", item);
        }
        return "detail.html";
    }
}
