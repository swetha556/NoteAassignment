package com.dxc.services;

import java.util.List;

import com.dxc.exception.NoteExistsException;
import com.dxc.model.Note;

public interface NoteService {

	Note newNote(Note note) throws NoteExistsException;

	Note updateNote(Note note);

	void deleteNoteById(int noteId);

	List<Note> getAllNotesByUser(String userId);

}
