package com.example.myapplication.model;

public class Compte {
    private Long id;
    private double solde;
    private String type;

    // Getters and setters
    public Long getId() {
        return id;

    }
    public Compte(Long id, double solde, String type) {
        this.id = id;
        this.solde = solde;
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
