package com.epul.oeuvres.metier;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by christian on 19/02/2017.
 */
public class PretEntityPK implements Serializable {
    private int idOeuvrepret;
    private int idAdherent;

    @Column(name = "id_oeuvrepret", insertable = false, updatable = false)
    @Id
    public int getIdOeuvrepret() {
        return idOeuvrepret;
    }

    public void setIdOeuvrepret(int idOeuvrepret) {
        this.idOeuvrepret = idOeuvrepret;
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

        PretEntityPK that = (PretEntityPK) o;

        if (idOeuvrepret != that.idOeuvrepret) return false;
        if (idAdherent != that.idAdherent) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOeuvrepret;
        result = 31 * result + idAdherent;
        return result;
    }
}
