package com.javainterns.bookingroom.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.service.RoomService;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping("/")
    public void create(@RequestBody Room room){
        roomService.create(room);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Room>> findById( @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@RequestBody Room room, @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(roomService.update(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
