package com.example.mydemo.controller;

import com.example.mydemo.model.entity.Order;
import com.example.mydemo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value="/admin")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/lunchAllOrdersPage")
    public String showAllOrders(Model model) {
        model.addAttribute("orders", orderService.findallOrders());
        return "allOrders";
    }

    @GetMapping("/lunchAddOrderPage")
    public String lunchAddOrderPage(Model model) {
        model.addAttribute("order", new Order());
        return "addOrder";
    }

    @PostMapping("/addOrder")
    public String addOrder(Order order) {
        orderService.addOrder(order);
        return "redirect:/admin/lunchAllOrdersPage";

    }

    @GetMapping("/editOrder/{id}")
    public String lunchEditOrderPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", orderService.findOrderById(id));
        return "editOrder";

    }

    @PostMapping("/updateOrder")
    public String updateOrder(Order order) {
        orderService.updateOrder(order);
        return "redirect:/admin/lunchAllOrdersPage";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return "redirect:/admin/lunchAllOrdersPage";
    }

}
