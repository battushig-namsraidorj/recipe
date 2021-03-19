package com.profnaya.recipe.converter;

import com.profnaya.recipe.command.IngredientCommand;
import com.profnaya.recipe.domain.Ingredient;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomcConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomcConverter) {
        this.uomcConverter = uomcConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomcConverter.convert(source.getUom()));
        return ingredient;
    }
}
