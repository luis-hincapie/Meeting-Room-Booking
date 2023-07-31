package com.javainterns.bookingroom.controller;


import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.service.RoomService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
