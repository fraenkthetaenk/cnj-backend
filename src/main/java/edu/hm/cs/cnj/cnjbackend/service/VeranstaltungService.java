package edu.hm.cs.cnj.cnjbackend.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hm.cs.cnj.cnjbackend.persistence.Teilnahme;
import edu.hm.cs.cnj.cnjbackend.persistence.TeilnahmeStatus;
import edu.hm.cs.cnj.cnjbackend.persistence.Veranstaltung;
import edu.hm.cs.cnj.cnjbackend.persistence.VeranstaltungRepository;

@Service
@Transactional
public class VeranstaltungService {
	@Autowired
	private VeranstaltungRepository repository;

	public Long erzeugeVeranstaltung() {
		Veranstaltung veranstaltung = new Veranstaltung();
		repository.save(veranstaltung);
		return veranstaltung.getId();
	}

	public Long erzeugeVeranstaltung(String titel, String beschreibung, Date beginn) {
		Veranstaltung veranstaltung = new Veranstaltung(titel, beschreibung, beginn);
		repository.save(veranstaltung);
		return veranstaltung.getId();
	}

	public void fuegeTeilnahmeHinzu(long key, String name, int begleiter) {
		Veranstaltung veranstaltung = repository.findOne(key);
		Teilnahme teilnahme = new Teilnahme(name, begleiter);
		veranstaltung.add(teilnahme);
	}

	public void sageOffeneTeilnahmenAbBis(Date date) {
		List<Veranstaltung> v = repository.findByBeginnBefore(date);

		for (Veranstaltung veranstaltung : v) {
			Set<Teilnahme> e = veranstaltung.getEinladungen();
			for (Teilnahme teilnahme : e) {
				teilnahme.setStatus(TeilnahmeStatus.ABSAGE);
			}
		}
	}
}
