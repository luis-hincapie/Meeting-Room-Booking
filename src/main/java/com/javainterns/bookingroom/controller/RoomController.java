package com.javainterns.bookingroom.controller;


import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.service.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(path = "/room")
public class RoomController {
    @Autowired
    RoomService roomService;

/*    @PostMapping("/")
    public String create(@Valid @RequestBody Room room, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {

            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return "Error de validaciónnnnnn ingrese un numero mayor ala hora de inicio : "  + errorMessage;
        }else{

            roomService.create(room);
            return "Evento creado con éxito.";
        }


    }*/

    @PostMapping("/")
    public String create(@Valid @RequestBody Room room, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Manejar los errores de validación aquí
            StringBuilder errorMessage = new StringBuilder("Error de validación: ");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }
            return errorMessage.toString();
        } else {
            roomService.create(room);
            return "Evento creado con éxito.";
        }
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
