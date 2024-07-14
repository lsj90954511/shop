package com.example.shop.item;

import com.example.shop.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final S3Service s3Service;
    private final CommentRepository commentRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemService.getAllItems();
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
    String addPost(String title, Integer price, String username){

        itemService.saveItem(title, price, username);

        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(Model model, @PathVariable Long id) throws Exception{
        //throw new Exception();

        List comments = commentRepository.findAllByParentId(id);
        model.addAttribute("comments", comments);

        Optional<Item> result = itemService.getItemById(id);

        if(result.isPresent()) {
            Item item = result.get();
            model.addAttribute("detail", item);
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

    //수정 페이지에 아이템 정보 세팅
    @GetMapping("/edit/{id}")
    String set(Model model, @PathVariable Long id) {
        Optional<Item> result = itemService.getItemById(id);

        if(result.isPresent()) {
            Item item = result.get();
            model.addAttribute("data", item);
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }

    //아이템 정보 수정하기
    /*@PostMapping("/edit/{id}")
    String updateItem(String title, Integer price, @PathVariable Long id) {
        itemService.updateItem(id, title, price);
        return "redirect:/list";
    }*/
    //아이템 정보 수정하기(강의 버전)
    @PostMapping("/edit")
    String updateItem(String title, Integer price, Long id) {
        itemService.updateItem(id, title, price);
        return "redirect:/list";
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> test1(@RequestParam Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/list/{page}")
    String getListPage(Model model, @PathVariable Integer page) {

        Page<Item> result = itemRepository.findPageBy(PageRequest.of(page - 1,5));
        int totalPages = result.getTotalPages();
        model.addAttribute("items", result);
        model.addAttribute("totalPages", totalPages);

        return "list.html";
    }

    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename) {

        System.out.println(filename);
        var result = s3Service.createPresignedUrl("test/" + filename);
        System.out.println(result);
        return result;
    }

    @PostMapping("/search")
    String postSearch(Model model, @RequestParam String searchText) {

        var result = itemRepository.rawQuery1(searchText);
        System.out.println(result);
        model.addAttribute("search", result);

        return "search.html";
    }

}
