package tn.esprit.gaspillagezero.services.EventManagement_Servvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.EventManagement.Event;
import tn.esprit.gaspillagezero.repository.EventManagement_Repository.EventRepository;
import tn.esprit.gaspillagezero.repository.EventManagement_Repository.MenuRepository;
import tn.esprit.gaspillagezero.repository.Marketplace_Repository.PromotionsRepository;

import java.util.List;
@Service
public class EventServiceImplement implements IEventService{
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(long eventid) {
        eventRepository.deleteById(eventid);

    }

    @Override
    public List<Event> retieveAllevents() {
        return eventRepository.findAll();
    }

    @Override
    public Event retieveEvent(long eventid) {
        return eventRepository.findById(eventid).get();
    }
}
