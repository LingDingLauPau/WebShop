package com.example.webshop.Controller;

import com.example.webshop.Model.Items;
import com.example.webshop.Service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ItemsService itemsService;

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    // Show All Items
    @GetMapping("/showAllItems")
    public String showAllItems(Model model) {
        List<Items> itemsList = itemsService.fetchAllItems();
        model.addAttribute("itemsList", itemsList);
        return "home/showAllItems";
    }

    //Add an Item
    @GetMapping("/addItem")
    public String addItem(Model model) {
        List<Items> items = itemsService.fetchAllItems();
        model.addAttribute("items", items);
        return "home/addItem";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute Items items) {
        return itemsService.addItem(items);
    }

    //Edit an item
    @GetMapping("/updateItem/{id}")
    public String updateItem(@PathVariable("id") int id, Model model) {
        Items items = itemsService.findById(id);
        model.addAttribute("items", items);
        return "home/updateItem";
    }

    @PostMapping("/updateItem/{id}")
    public String updateItem(@PathVariable("id") int id, @ModelAttribute Items items) {
        itemsService.updateItem(items, id);
        return "redirect:/showAllItems";
    }

    //Delete an item
    @GetMapping("/deleteItem/{id}")
    public String deleteItem ( @PathVariable("id") int id){
        return itemsService.deleteItem(id);
    }

}
