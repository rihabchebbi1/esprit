package tn.esprit.gaspillagezero.services.EventManagement_Servvice;

import tn.esprit.gaspillagezero.entites.EventManagement.Event;

import java.util.List;

public interface IEventService {
    Event addEvent(Event event);
    Event updateEvent(Event event);
    void deleteEvent(long eventid);
    List<Event> retieveAllevents();
    Event retieveEvent(long eventid);
}
