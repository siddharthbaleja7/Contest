package com.siddharth.contest.Controllers;

import com.siddharth.contest.CartService.CartService;
import com.siddharth.contest.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartService cs;

    // getSingleCart
    @GetMapping("/carts/{id}")
    public Cart getSingleCart(@PathVariable("id") Long xyz){
        return cs.getSingleCart(xyz);
    }

    //getAllCart
    @GetMapping("/carts")
    public List<Cart> getAllCart(){
    return cs.getAllCart();
    }

    //add a new Cart
    @PostMapping("/carts")
    public Cart addNewCart(@RequestBody Cart cart){
        return cs.addNewCart(cart);
    }

    //delete a cart
    @DeleteMapping("/carts/{id}")
    public String deleteCart(@PathVariable("id") Long xyz){
        cs.deleteCart(xyz);
        return "ME BADHIYA TU BHI BADHIYA";
    }

    //update a cart
    @PutMapping("/carts")
    public Cart updateCart(@RequestBody Cart cart){
        return cs.updateCart(cart);
    }

}
