package com.example.demoanimalbot.model.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@NoArgsConstructor

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue
    private long id;

    private String mediaType;


    @Column(columnDefinition = "oid")
    private byte[] data;

    public Photo(String mediaType, byte[] data) {
        this.mediaType = mediaType;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
