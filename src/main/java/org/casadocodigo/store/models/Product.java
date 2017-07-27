package org.casadocodigo.store.models;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String description;
	private int pages;
	
	@DateTimeFormat
	private Calendar launchDate;

	@ElementCollection
	private List<Price> prices;
	
	private String summaryPath;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<Price> getPrices() {
		return prices;
	}
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 
	public String getSummaryPath() {
		return summaryPath;
	}
	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}
	public Calendar getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Calendar launchDate) {
		this.launchDate = launchDate;
	}
	public BigDecimal getPriceFor(PriceType priceType) {

		return prices.stream()
				.filter(price -> price.getType().equals(priceType))
				.findFirst().get().getValue();
		
	}
}
