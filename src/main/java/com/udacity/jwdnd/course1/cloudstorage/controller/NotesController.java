package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller()

public class NotesController {
    private NoteService noteService;
    private UserService userService;

    public NotesController(NoteService noteService, UserService userService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String getNotes(Authentication authentication) {
        return "notes";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") int noteid) {
        noteService.deleteNote(noteid);
        return "redirect:/result?delnotesuccess";
    }

    @PostMapping("/notes")
    public String postNotes (Note note, Authentication authentication){
        int userId = userService.getUserId(authentication.getName());
        note.setUserId(userId);
        if (note.getNoteId() != null) {
            noteService.updateNote(note);
            return "redirect:/result?editnotesuccess";
        }
        else {
            noteService.insertNote(note);
            return "redirect:/result?addnotesuccess";
        }
    }
}
