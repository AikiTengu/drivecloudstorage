package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NotesMapper notesMapper;

    public NoteService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public List<Note> getNotes(int userId) {
        return notesMapper.getNotes(userId);
    }

    public void insertNote(Note note) {
            notesMapper.insert(note);
    }

    public void updateNote(Note note) {
        notesMapper.update(note);
    }

    public void deleteNote(int noteid) {
        notesMapper.delete(noteid);
    }

}
