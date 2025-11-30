package com.example.projetandroid.models;

import com.google.firebase.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Property {
    private String propertyId;
    private String title;
    private String type; // maison/appartement
    private String configuration; // S+1, S+2, S+3...
    private double pricePerDay;
    private double pricePerWeek;
    private double pricePerMonth;
    private double distanceBeach; // en mètres
    private int maxCapacity;
    private String address;
    private String ownerContact;

    // Équipements
    private boolean airCondition;
    private boolean wifi;
    private boolean garage;
    private boolean pool;
    private boolean kitchen;
    private boolean seaView;
    private boolean terrace;
    private int bathrooms;

    private List<String> photos; // URLs des photos
    private String description;
    private List<String> samsars; // Liste des userIds des courtiers
    private String createdBy; // userId du créateur
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructeur vide requis par Firestore
    public Property() {
        this.photos = new ArrayList<>();
        this.samsars = new ArrayList<>();
        this.createdAt = Timestamp.now();
        this.updatedAt = Timestamp.now();
    }

    // Constructeur avec paramètres
    public Property(String title, String type, String configuration,
                    double pricePerDay, String address) {
        this();
        this.title = title;
        this.type = type;
        this.configuration = configuration;
        this.pricePerDay = pricePerDay;
        this.address = address;
    }

    // === GETTERS ===

    public String getPropertyId() {
        return propertyId;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getConfiguration() {
        return configuration;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public double getPricePerWeek() {
        return pricePerWeek;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public double getDistanceBeach() {
        return distanceBeach;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getAddress() {
        return address;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public boolean isAirCondition() {
        return airCondition;
    }

    public boolean isWifi() {
        return wifi;
    }

    public boolean isGarage() {
        return garage;
    }

    public boolean isPool() {
        return pool;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public boolean isSeaView() {
        return seaView;
    }

    public boolean isTerrace() {
        return terrace;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSamsars() {
        return samsars;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    // === SETTERS ===

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setPricePerWeek(double pricePerWeek) {
        this.pricePerWeek = pricePerWeek;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public void setDistanceBeach(double distanceBeach) {
        this.distanceBeach = distanceBeach;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public void setAirCondition(boolean airCondition) {
        this.airCondition = airCondition;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public void setSeaView(boolean seaView) {
        this.seaView = seaView;
    }

    public void setTerrace(boolean terrace) {
        this.terrace = terrace;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSamsars(List<String> samsars) {
        this.samsars = samsars;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // === MÉTHODES UTILITAIRES ===

    /**
     * Convertir l'objet Property en Map pour Firestore
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("type", type);
        map.put("configuration", configuration);
        map.put("pricePerDay", pricePerDay);
        map.put("pricePerWeek", pricePerWeek);
        map.put("pricePerMonth", pricePerMonth);
        map.put("distanceBeach", distanceBeach);
        map.put("maxCapacity", maxCapacity);
        map.put("address", address);
        map.put("ownerContact", ownerContact);

        // Créer un sous-objet pour les équipements
        Map<String, Boolean> equipment = new HashMap<>();
        equipment.put("airCondition", airCondition);
        equipment.put("wifi", wifi);
        equipment.put("garage", garage);
        equipment.put("pool", pool);
        equipment.put("kitchen", kitchen);
        equipment.put("seaView", seaView);
        equipment.put("terrace", terrace);
        map.put("equipment", equipment);

        map.put("bathrooms", bathrooms);
        map.put("photos", photos);
        map.put("description", description);
        map.put("samsars", samsars);
        map.put("createdBy", createdBy);
        map.put("createdAt", createdAt);
        map.put("updatedAt", Timestamp.now()); // Mise à jour automatique

        return map;
    }

    /**
     * Ajouter un courtier à la liste des samsars
     */
    public void addSamsar(String userId) {
        if (this.samsars == null) {
            this.samsars = new ArrayList<>();
        }
        if (!this.samsars.contains(userId)) {
            this.samsars.add(userId);
        }
    }

    /**
     * Retirer un courtier de la liste des samsars
     */
    public void removeSamsar(String userId) {
        if (this.samsars != null) {
            this.samsars.remove(userId);
        }
    }

    /**
     * Vérifier si un courtier fait partie des samsars
     */
    public boolean hasSamsar(String userId) {
        return this.samsars != null && this.samsars.contains(userId);
    }

    /**
     * Ajouter une photo à la liste
     */
    public void addPhoto(String photoUrl) {
        if (this.photos == null) {
            this.photos = new ArrayList<>();
        }
        this.photos.add(photoUrl);
    }

    /**
     * Obtenir la première photo (photo principale)
     */
    public String getMainPhoto() {
        if (photos != null && !photos.isEmpty()) {
            return photos.get(0);
        }
        return null;
    }

    /**
     * Compter le nombre total d'équipements disponibles
     */
    public int getEquipmentCount() {
        int count = 0;
        if (airCondition) count++;
        if (wifi) count++;
        if (garage) count++;
        if (pool) count++;
        if (kitchen) count++;
        if (seaView) count++;
        if (terrace) count++;
        return count;
    }

    /**
     * Obtenir une description courte des équipements principaux
     */
    public String getEquipmentSummary() {
        List<String> items = new ArrayList<>();
        if (wifi) items.add("WiFi");
        if (pool) items.add("Piscine");
        if (airCondition) items.add("Climatisation");
        if (seaView) items.add("Vue mer");

        if (items.isEmpty()) {
            return "Aucun équipement";
        }

        return String.join(" • ", items);
    }

    /**
     * Calculer le prix pour un nombre de jours donné
     */
    public double calculatePrice(int days) {
        if (days >= 30 && pricePerMonth > 0) {
            return pricePerMonth;
        } else if (days >= 7 && pricePerWeek > 0) {
            int weeks = days / 7;
            int remainingDays = days % 7;
            return (weeks * pricePerWeek) + (remainingDays * pricePerDay);
        } else {
            return days * pricePerDay;
        }
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId='" + propertyId + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", configuration='" + configuration + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", address='" + address + '\'' +
                ", samsars=" + (samsars != null ? samsars.size() : 0) +
                '}';
    }
}