package com.epul.oeuvres.metier;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "baseoeuvres", catalog = "")
public class EntityUtilisateur {
    private Integer numUtil;
    private String nomUtil;
    private String motPasse;
    private String role;

    @Id
    @Column(name = "NumUtil", nullable = false)
    public Integer getNumUtil() {
        return numUtil;
    }

    public void setNumUtil(Integer numUtil) {
        this.numUtil = numUtil;
    }

    @Basic
    @Column(name = "NomUtil", nullable = false, length = 100)
    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    @Basic
    @Column(name = "MotPasse", nullable = false, length = 100)
    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 100)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityUtilisateur that = (EntityUtilisateur) o;
        return Objects.equals(numUtil, that.numUtil) &&
                Objects.equals(nomUtil, that.nomUtil) &&
                Objects.equals(motPasse, that.motPasse) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numUtil, nomUtil, motPasse, role);
    }
}
