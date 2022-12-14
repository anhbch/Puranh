package com.application.puranh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "description")
    private String description;

    @Column(name = "event_date")
    private LocalDate eventDate;
    //    @Column(name = "user_id", nullable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<MediaUpload> media;

    public Event() {
    }

    public Event(Long eventId, String eventName, String description, LocalDate eventDate,
                 User user, List<MediaUpload> media) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.user = user;
        this.media = media;
    }

    public Event(String eventName, String description, LocalDate eventDate, User user, List<MediaUpload> media) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.user = user;
        this.media = media;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MediaUpload> getMedia() {
        return media;
    }

    public void setMedia(List<MediaUpload> media) {
        this.media = media;
    }
}


