package com.kryscreasy.taco.controller;

import com.kryscreasy.taco.dto.Ingredient;
import com.kryscreasy.taco.dto.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("CHES", "Cheese", Ingredient.Type.CHEESE),
                new Ingredient("VEGG", "Veggies", Ingredient.Type.VEGGIES)
        );
        Ingredient.Type[] types = Ingredient.Type.values();

        for (Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {

        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }


    @PostMapping
    public String processDesign(@Valid Taco tacoNewDesign, Errors errors) {
        if (errors.hasErrors()){
            return "design";
        }
// Save the taco tacoNewDesign...
// We'll do this in chapter 3
        log.info("Processing tacoNewDesign: " + tacoNewDesign);
        return "redirect:/orders/current";
    }
}
