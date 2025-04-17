package tn.esprit.gaspillagezero.controllers.Event_Management_Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.gaspillagezero.entites.EventManagement.Event;
import tn.esprit.gaspillagezero.services.EventManagement_Servvice.IEventService;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:59887") // adjust frontend domain as needed
public class EventController {


    @Autowired
    IEventService eventService;

    // ✅ Add event with optional image file upload
    private final String uploadDir = "C:/uploads/"; // Le répertoire de sauvegarde des images
/*
    @PostMapping(value = "/addevent")
    public ResponseEntity<Event> addEvent(@RequestParam("title") String title,
                                          @RequestParam("description") String description,
                                          @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                          @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                          @RequestParam(value = "image", required = false) MultipartFile image) {

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setStartDate(startDate);
        event.setEndDate(endDate);

        if (image != null && !image.isEmpty()) {
            try {
                String filename = image.getOriginalFilename();  // No prefix
                Path filePath = Paths.get(uploadDir, filename);

                Files.write(filePath, image.getBytes());
                event.setImagePath(filename);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        Event saved = eventService.updateEvent(event);
        return ResponseEntity.ok(saved);
    }*/

    @PostMapping("/addevent")
    public Event addEvent(@RequestBody  Event event) {
        return eventService.addEvent(event);
    }

    @PutMapping("/updateevent")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @GetMapping("/retrieveAllevents")
    public List<Event> retieveAllevents() {
        return eventService.retieveAllevents();
    }

    @GetMapping("/retieveevent/{eventid}")
    public Event retieveEvent(@PathVariable long eventid) {
        return eventService.retieveEvent(eventid);
    }

    @DeleteMapping("/deleteevent/{eventid}")
    public void deleteEvent(@PathVariable long eventid) {
        eventService.deleteEvent(eventid);
    }
}
