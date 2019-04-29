package com.epul.oeuvres.metier;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by christian on 19/02/2017.
 */
public class ReservationEntityPK implements Serializable {
    private int idOeuvrevente;
    private int idAdherent;

    @Column(name = "id_oeuvrevente", insertable = false, updatable = false)
    @Id
    public int getIdOeuvrevente() {
        return idOeuvrevente;
    }

    public void setIdOeuvrevente(int idOeuvrevente) {
        this.idOeuvrevente = idOeuvrevente;
    }

    @Column(name = "id_adherent", insertable = false, updatable = false)
    @Id
    public int getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntityPK that = (ReservationEntityPK) o;

        if (idOeuvrevente != that.idOeuvrevente) return false;
        if (idAdherent != that.idAdherent) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOeuvrevente;
        result = 31 * result + idAdherent;
        return result;
    }
}
