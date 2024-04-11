package com.example.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price) {
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        List<Item> result = itemRepository.findAll();
        return result;
    }

    public Optional<Item> getItemById(Long id) {
        Optional<Item> result = itemRepository.findById(id);
        return result;
    }

    public void updateItem(Long id, String title, Integer price) {
        Item item = new Item();
        item.id = id;
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
}
