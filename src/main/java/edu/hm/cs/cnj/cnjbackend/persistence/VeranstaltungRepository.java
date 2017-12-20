package edu.hm.cs.cnj.cnjbackend.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface VeranstaltungRepository extends CrudRepository <Veranstaltung, Long> {

    List<Veranstaltung> findByBeginnBefore(Date date);

    List<Veranstaltung> findByBeginnAfter(Date date);

}