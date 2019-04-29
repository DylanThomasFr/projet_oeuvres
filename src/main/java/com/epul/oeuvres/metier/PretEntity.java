package com.epul.oeuvres.metier;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by christian on 19/02/2017.
 */
@Entity
@Table(name = "pret", schema = "baseoeuvres", catalog = "")
@IdClass(PretEntityPK.class)
public class PretEntity {
    private int idOeuvrepret;
    private int idAdherent;
    private Date dateReservation;
    private Date dateRendu;
    private OeuvrepretEntity oeuvrepretByIdOeuvrepret;
    private AdherentEntity adherentByIdAdherent;

    @Id
    @Column(name = "id_oeuvrepret")
    public int getIdOeuvrepret() {
        return idOeuvrepret;
    }

    public void setIdOeuvrepret(int idOeuvrepret) {
        this.idOeuvrepret = idOeuvrepret;
    }

    @Id
    @Column(name = "id_adherent")
    public int getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }

    @Basic
    @Column(name = "date_reservation")
    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Basic
    @Column(name = "date_rendu")
    public Date getDateRendu() {
        return dateRendu;
    }

    public void setDateRendu(Date dateRendu) {
        this.dateRendu = dateRendu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PretEntity that = (PretEntity) o;

        if (idOeuvrepret != that.idOeuvrepret) return false;
        if (idAdherent != that.idAdherent) return false;
        if (dateReservation != null ? !dateReservation.equals(that.dateReservation) : that.dateReservation != null)
            return false;
        if (dateRendu != null ? !dateRendu.equals(that.dateRendu) : that.dateRendu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOeuvrepret;
        result = 31 * result + idAdherent;
        result = 31 * result + (dateReservation != null ? dateReservation.hashCode() : 0);
        result = 31 * result + (dateRendu != null ? dateRendu.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_oeuvrepret", referencedColumnName = "id_oeuvrepret", nullable = false)
    public OeuvrepretEntity getOeuvrepretByIdOeuvrepret() {
        return oeuvrepretByIdOeuvrepret;
    }

    public void setOeuvrepretByIdOeuvrepret(OeuvrepretEntity oeuvrepretByIdOeuvrepret) {
        this.oeuvrepretByIdOeuvrepret = oeuvrepretByIdOeuvrepret;
    }

    @ManyToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent", nullable = false)
    public AdherentEntity getAdherentByIdAdherent() {
        return adherentByIdAdherent;
    }

    public void setAdherentByIdAdherent(AdherentEntity adherentByIdAdherent) {
        this.adherentByIdAdherent = adherentByIdAdherent;
    }
}
