package com.example.webshop.Service;

import com.example.webshop.Model.Items;
import com.example.webshop.Repository.ItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ItemsService {

    @Autowired
    ItemsRepo itemsRepo;

    public List<Items> fetchAllItems() {
        return itemsRepo.fetchAllItems();
    }

    public String addItem(Items items) {
        itemsRepo.addNew(items);
        return "redirect:/showAllItems";
    }

    public void updateItem(Items items, int id){
        itemsRepo.updateItem(items, id);
    }

   public String deleteItem(int id){
       itemsRepo.deleteItem(id);
       return "redirect:/showAllItems";
   }

    public Items findById(int id) {
        return itemsRepo.findById(id);
    }
}
