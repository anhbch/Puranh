package com.application.puranh.controller;

import com.application.puranh.model.User;
import com.application.puranh.service.DTO.EventDto;
import com.application.puranh.service.EventService;
import com.application.puranh.model.Event;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class EventController {

    @Autowired
    private EventService eventService;

    private final Logger logger = LoggerFactory.getLogger(User.class);

    @GetMapping (path = "/event/{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long eventId) {
        logger.debug("Request to get an event by event's id");
        Optional<EventDto> eventDto = eventService.findOne(eventId);
        return ResponseUtil.wrapOrNotFound(eventDto);
    }

    @GetMapping (path = "/events/{userId}")
    public ResponseEntity<List<EventDto>> getEventByUserId (@PathVariable(name = "userId") Long userId) {
        logger.debug("Request to get events by user id");
        return ResponseEntity.ok(eventService.findByUser(userId));
    }

    @PutMapping(path = "/edit-event")
    public ResponseEntity<EventDto> EventDto (@Valid @RequestBody EventDto eventDto) {
        logger.debug("Request to update an event");
        if (eventDto.getEventId() == null) {
            throw new IllegalArgumentException("Event's id cannot be null");
        }
        if (eventService.findOne(eventDto.getEventId()).isPresent()) {
            EventDto result = eventService.save(eventDto);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/create-event")
    public ResponseEntity<EventDto> createNewEvent (@RequestBody EventDto eventDto) throws URISyntaxException {
        logger.debug("Request to create new event");
        if (eventDto.getEventId() != null) {
            throw new IllegalStateException("Event already exists!");
        }
        EventDto result = eventService.save(eventDto);
        return ResponseEntity.created(new URI("events/create" + result.getEventId()))
                .body(result);
    }

    @DeleteMapping (path = "/events/{eventId}")
    public ResponseEntity<Void> deleteAnEvent (@PathVariable(name = "eventId") Long eventId) {
        logger.debug("Request to delete and event");
        eventService.deleteAnEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
