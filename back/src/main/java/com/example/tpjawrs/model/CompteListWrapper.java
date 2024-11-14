package com.example.tpjawrs.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "comptes")
public class CompteListWrapper {

    private List<Compte> comptes;

    public CompteListWrapper() {}

    public CompteListWrapper(List<Compte> comptes) {
        this.comptes = comptes;
    }

    @XmlElement(name = "compte")
    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}
