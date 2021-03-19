package com.profnaya.recipe.converter;

import com.profnaya.recipe.domain.Notes;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, com.profnaya.recipe.command.NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public com.profnaya.recipe.command.NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }
        final com.profnaya.recipe.command.NotesCommand notesCommand = new com.profnaya.recipe.command.NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        return notesCommand;
    }
}
