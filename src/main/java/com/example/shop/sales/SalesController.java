package com.example.shop.sales;

import com.example.shop.member.CustomUser;
import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SalesController {
    private final SalesRepository salesRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/order")
    String order(@RequestParam String itemName, @RequestParam Integer price, @RequestParam Integer count, Authentication auth) {
        CustomUser user = (CustomUser) auth.getPrincipal();
        var member = new Member();
        member.setId(user.id);

        Sales sales = new Sales();
        sales.setItemName(itemName);
        sales.setPrice(price);
        sales.setCount(count);
        sales.setMember(member);

        salesRepository.save(sales);

        return "list.html";
    }

    @GetMapping("/order/all")
    String getOrderAll(Model model){

        var result = salesRepository.customFindAll();
        System.out.println(result);
        /*var salesDto = new SalesDto();
        salesDto.itemName = result.get(0).getItemName();
        salesDto.price = result.get(0).getPrice();
        salesDto.username = result.get(0).getMember().getDisplayName();*/
        model.addAttribute("allSales", result);

        return "sales.html";
    }

    class SalesDto {
        public String itemName;
        public Integer price;
        public String username;
    }
}
