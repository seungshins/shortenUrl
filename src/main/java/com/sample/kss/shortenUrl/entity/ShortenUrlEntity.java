package com.sample.kss.shortenUrl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(
		name = "SHORTEN_URL_INDEX_SEQ_GENERATOR",
		sequenceName = "SHORTEN_URL_INDEX_SEQ",
		initialValue = 1, allocationSize = 1
		)
public class ShortenUrlEntity implements Serializable {

	private static final long serialVersionUID = -2512878900997440414L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHORTEN_URL_INDEX_SEQ_GENERATOR")
	private Long index;
	
	@Column(nullable = false)
	private String originUrl;
	
	private String shortenUrl;
	
	private Date creDt;
	
	private String stringIndex;
	
	public Date getCreDt() {
		return creDt;
	}

	public void setCreDt(Date creDt) {
		this.creDt = creDt;
	}

	public String getStringIndex() {
		return stringIndex;
	}

	public void setStringIndex(String stringIndex) {
		this.stringIndex = stringIndex;
	}

	protected ShortenUrlEntity() {}
	
	public ShortenUrlEntity( Long index ) {
		this.index = index;
	}
	
	public ShortenUrlEntity( Long index, String originUrl) {
		this.index = index;
	}
	
	public ShortenUrlEntity(String originUrl, String shortenUrl) {
		this.originUrl = originUrl;
		this.shortenUrl = shortenUrl;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public String getShortenUrl() {
		return shortenUrl;
	}

	public void setShortenUrl(String shortenUrl) {
		this.shortenUrl = shortenUrl;
	}
	
	@Override
	public String toString() {
		return "[{index : " + index + "}" +
				"{originUrl : " + originUrl + "}" +
				"{shortenUrl : " + shortenUrl + "}" + 
				"{creDt : " + creDt + "}]";
	}
}
