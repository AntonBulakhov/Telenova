package com.telenova.backend.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "value_measure", schema = "telenovadb", catalog = "")
public class ValueMeasureEntity {
    private int id;
    private String measurement;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "measurement")
    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueMeasureEntity that = (ValueMeasureEntity) o;

        if (id != that.id) return false;
        if (measurement != null ? !measurement.equals(that.measurement) : that.measurement != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (measurement != null ? measurement.hashCode() : 0);
        return result;
    }
}
