package com.workintech.s18d1.controller;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.dao.BurgerDaoImpl;

import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/burgers")
@RequiredArgsConstructor
@Slf4j
public class BurgerController {

    private final BurgerDaoImpl burgerDao;

    @GetMapping
    public List<Burger> getAllBurgers() {
        log.info("Fetching all burgers");
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getBurgerById(@PathVariable Long id) {
        log.info("Fetching burger with ID: {}", id);
        Burger burger = burgerDao.findById(id);
        if (burger == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        return burger;
    }

    @PostMapping
    public Burger addBurger(@RequestBody Burger burger) {
        log.info("Adding new burger: {}", burger);
        BurgerValidation.validateBurger(burger);
        return burgerDao.save(burger);
    }

    @PutMapping("/{id}")
    public Burger updateBurger(@PathVariable Long id, @RequestBody Burger burger) {
        log.info("Updating burger with ID: {}", id);
        Burger existingBurger = burgerDao.findById(id);
        if (existingBurger == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        burger.setId(id);
        BurgerValidation.validateBurger(burger);
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public void deleteBurger(@PathVariable Long id) {
        burgerDao.remove(id);
    }

    @GetMapping("/findByPrice")
    public List<Burger> getBurgersByPrice(@RequestBody double price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/findByBreadType")
    public List<Burger> getBurgersByBreadType(@RequestBody BreadType breadType) {
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/findByContent")
    public List<Burger> getBurgersByContent(@RequestBody String content) {
        return burgerDao.findByContent(content);
    }
}

