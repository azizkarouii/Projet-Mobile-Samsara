package com.example.projetandroid.models;

import com.google.firebase.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Reservation {
    private String reservationId;
    private String propertyId;
    private String samsarId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String status; // available/pending/reserved
    private String clientName;
    private String clientPhone;
    private double advanceAmount;
    private double totalAmount;
    private String notes;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructeur vide
    public Reservation() {
    }

    // Getters et Setters
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getSamsarId() {
        return samsarId;
    }

    public void setSamsarId(String samsarId) {
        this.samsarId = samsarId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Convertir en Map pour Firestore
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("propertyId", propertyId);
        map.put("samsarId", samsarId);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("status", status);
        map.put("clientName", clientName);
        map.put("clientPhone", clientPhone);
        map.put("advanceAmount", advanceAmount);
        map.put("totalAmount", totalAmount);
        map.put("notes", notes);
        map.put("createdAt", createdAt);
        map.put("updatedAt", updatedAt);
        return map;
    }
}