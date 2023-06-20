package com.dxc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.exception.NoteExistsException;
import com.dxc.model.Note;
import com.dxc.model.User;
import com.dxc.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	NoteRepository noterepository;
	
	@Override
	public Note newNote(Note note) throws NoteExistsException {
		final boolean existById = this.noterepository.existsById(note.getNoteId());
		if(existById) {
			throw new NoteExistsException("Note Already Exists");
		}
		return this.noterepository.save(note);
	}
	
	@Override
	public Note updateNote(Note note) {
		return this.noterepository.save(note);
	}

	@Override
	public void deleteNoteById(int noteId){
		Optional<Note> note=noterepository.findById(noteId);
		if(note.isPresent())
			noterepository.deleteById(noteId);
		
//		noterepository.deleteById(noteId);
	}
	
	@Override
	public List<Note> getAllNotesByUser(String userId) {
		return noterepository.findNotesBynoteCreatedBy(userId);
	}
}
