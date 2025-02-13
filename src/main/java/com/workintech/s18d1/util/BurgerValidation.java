package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {
    public static void validateBurger(Burger burger) {
        if (burger.getName() == null || burger.getName().isEmpty()) {
            throw new BurgerException("Burger name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (burger.getPrice() == null || burger.getPrice() <= 0) {
            throw new BurgerException("Burger price must be greater than zero", HttpStatus.BAD_REQUEST);
        }
    }
}
