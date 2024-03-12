package com.siddharth.contest.CartService;

import com.siddharth.contest.cartDtos.FakeStoreDto;
import com.siddharth.contest.cartDtos.ProductQuantityDto;
import com.siddharth.contest.models.Cart;
import com.siddharth.contest.models.ProductQuantity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    RestTemplate restTemplate = new RestTemplate();
    String url =  "https://fakestoreapi.com/carts";


    // GetSingeCart
            public Cart getSingleCart(Long id) {
                FakeStoreDto response = restTemplate.getForObject(url + "/" + id, FakeStoreDto.class);
                Cart cart = new Cart();
                assert response != null;
                cart.setId(response.getId());
                cart.setUserId(response.getUserId());
                cart.setDate(response.getDate());

                List<ProductQuantity> productQuantities = new ArrayList<>();
                for (ProductQuantityDto productQuantityDto : response.getProducts()) {
                    ProductQuantity productQuantity = new ProductQuantity();
                    productQuantity.setProductId(productQuantityDto.getProductId());
                    productQuantity.setQuantity(productQuantityDto.getQuantity());
                    productQuantities.add(productQuantity);
                }
                cart.setProducts(productQuantities);

                return cart;
            }

        public Cart addNewCart(Cart cart) {
            FakeStoreDto request = new FakeStoreDto();
            request.setDate(cart.getDate());
            request.setId(cart.getId());
            request.setUserId(cart.getUserId());

            List<ProductQuantityDto> productQuantitiesDto = new ArrayList<>();
            for (ProductQuantity productQuantity : cart.getProducts()) {
                ProductQuantityDto productQuantityDto = new ProductQuantityDto();
                productQuantityDto.setProductId(productQuantity.getProductId());
                productQuantityDto.setQuantity(productQuantity.getQuantity());
                productQuantitiesDto.add(productQuantityDto);
            }
            request.setProducts(productQuantitiesDto);

            FakeStoreDto response = restTemplate.postForObject(url, request, FakeStoreDto.class);
            Cart newCart = new Cart();
            assert response != null;
            newCart.setId(response.getId());
            newCart.setUserId(response.getUserId());
            newCart.setDate(response.getDate());


            List<ProductQuantity> productQuantities = new ArrayList<>();
            for (ProductQuantityDto productQuantityDto : response.getProducts()) {
                ProductQuantity productQuantity = new ProductQuantity();
                productQuantity.setProductId(productQuantityDto.getProductId());
                productQuantity.setQuantity(productQuantityDto.getQuantity());
                productQuantities.add(productQuantity);
            }
            newCart.setProducts(productQuantities);

            return newCart;
        }

        public void deleteCart(Long id){
            restTemplate.delete(url + "/" + id);
        }

        public Cart updateCart(Cart cart){
                FakeStoreDto response = new FakeStoreDto();
                response.setId(response.getId());
                response.setUserId(response.getUserId());
                response.setDate(response.getDate());

                List<ProductQuantityDto> cloneDto = new ArrayList<>();
            for (ProductQuantityDto productQuantityDto : response.getProducts()) {
                productQuantityDto.setProductId(productQuantityDto.getProductId());
                productQuantityDto.setQuantity(productQuantityDto.getQuantity());
                cloneDto.add(productQuantityDto);
            }
            response.setProducts(cloneDto);
            restTemplate.put(url+ "/" + cart.getUserId(),FakeStoreDto.class);
            return cart;
        }

        public List<Cart> getAllCart(){
            ResponseEntity<Cart[]> response = restTemplate.getForEntity(url, Cart[].class);
            List<Cart> allCarts = List.of(response.getBody());
            return allCarts;
        }
}










