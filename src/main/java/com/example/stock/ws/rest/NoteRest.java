package com.example.stock.ws.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.Note;
import com.example.stock.service.facade.NoteService;
@RestController
@RequestMapping("/gestionDesEmployee-Api/Note/")
public class NoteRest {
@Autowired
private NoteService noteService;

@GetMapping("findByMention/metion/{metion}")
public List<Note> findByMention(@PathVariable Double metion) {
	return noteService.findByMention(metion);
}


@GetMapping("findByid/id/{id}")
public Note findByid(@PathVariable Long id) {
	return noteService.findByid(id);
}


@GetMapping("findAll")
public List<Note> findAll() {
	return noteService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Note note) {
	return noteService.save(note);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return noteService.deleteById(id);
}
}
