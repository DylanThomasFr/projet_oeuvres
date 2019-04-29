package com.epul.oeuvres.metier;

import javax.persistence.*;

/**
 * Created by christian on 19/02/2017.
 */
@Entity
@Table(name = "adherent", schema = "baseoeuvre", catalog = "")
public class AdherentEntity {
    private int idAdherent;
    private String nomAdherent;
    private String prenomAdherent;
    private String villeAdherent;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_adherent")
    public int getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }

    @Basic
    @Column(name = "nom_adherent")
    public String getNomAdherent() {
        return nomAdherent;
    }

    public void setNomAdherent(String nomAdherent) {
        this.nomAdherent = nomAdherent;
    }

    @Basic
    @Column(name = "prenom_adherent")
    public String getPrenomAdherent() {
        return prenomAdherent;
    }

    public void setPrenomAdherent(String prenomAdherent) {
        this.prenomAdherent = prenomAdherent;
    }

    @Basic
    @Column(name = "ville_adherent")
    public String getVilleAdherent() {
        return villeAdherent;
    }

    public void setVilleAdherent(String villeAdherent) {
        this.villeAdherent = villeAdherent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdherentEntity that = (AdherentEntity) o;

        if (idAdherent != that.idAdherent) return false;
        if (nomAdherent != null ? !nomAdherent.equals(that.nomAdherent) : that.nomAdherent != null) return false;
        if (prenomAdherent != null ? !prenomAdherent.equals(that.prenomAdherent) : that.prenomAdherent != null)
            return false;
        if (villeAdherent != null ? !villeAdherent.equals(that.villeAdherent) : that.villeAdherent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdherent;
        result = 31 * result + (nomAdherent != null ? nomAdherent.hashCode() : 0);
        result = 31 * result + (prenomAdherent != null ? prenomAdherent.hashCode() : 0);
        result = 31 * result + (villeAdherent != null ? villeAdherent.hashCode() : 0);
        return result;
    }
}
