package com.circron.resource.table;

import com.circron.meta.EntityType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
@EntityType(value = 12L, recordEvents = true, recordStatus = true)
@Table(name = "stream")
public class Stream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) NOT NULL AUTO_INCREMENT")
    private Long id;
    @Column(name = "stream_type_id", columnDefinition = "int(11) NOT NULL DEFAULT 1")
    private Long streamTypeId;
    @Column(name = "sample_rate", columnDefinition = "varchar(255) NOT NULL")
    private String sampleRate;
    @Column(name = "sample_size", columnDefinition = "int(11) NOT NULL")
    private Long sampleSize;
    @Column(name = "sample_signed", columnDefinition = "tinyint(1) NOT NULL")
    private Boolean sampleSigned;
    @Column(name = "sample_endian", columnDefinition = "varchar(255) NOT NULL")
    private String sampleEndian;
    @Column(name = "sample_encoding", columnDefinition = "varchar(255) NOT NULL")
    private String sampleEncoding;
    @Column(name = "sample_channels", columnDefinition = "int(11) NOT NULL")
    private Long sampleChannels;

    public Long getId() {
        return id;
    }

    public Long getStreamTypeId() {
        return streamTypeId;
    }

    public void setStreamTypeId(Long streamTypeId) {
        this.streamTypeId = streamTypeId;
    }

    public String getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(String sampleRate) {
        this.sampleRate = sampleRate;
    }

    public Long getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(Long sampleSize) {
        this.sampleSize = sampleSize;
    }

    public Boolean getSampleSigned() {
        return sampleSigned;
    }

    public void setSampleSigned(Boolean sampleSigned) {
        this.sampleSigned = sampleSigned;
    }

    public String getSampleEndian() {
        return sampleEndian;
    }

    public void setSampleEndian(String sampleEndian) {
        this.sampleEndian = sampleEndian;
    }

    public String getSampleEncoding() {
        return sampleEncoding;
    }

    public void setSampleEncoding(String sampleEncoding) {
        this.sampleEncoding = sampleEncoding;
    }

    public Long getSampleChannels() {
        return sampleChannels;
    }

    public void setSampleChannels(Long sampleChannels) {
        this.sampleChannels = sampleChannels;
    }
}
