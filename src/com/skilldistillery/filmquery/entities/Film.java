package com.skilldistillery.filmquery.entities;

public class Film {
//	Complete the Film class with attributes that map to the columns 
//	of the film table. Use appropriate datatypes and Java naming conventions.
//	Provide getters and setters, and appropriate constructors. Also add a good toString, and equals and hashCode methods.
	private int id;
	private String title;
	private String description;
	private int release_year;
	private int language_id;
	private int rental_duation;
	private int rental_rate;
	private int length;
	private double replacement_cost;
	private String rating;
	private String special_featues;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRelease_year() {
		return release_year;
	}
	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}
	public int getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}
	public int getRental_duation() {
		return rental_duation;
	}
	public void setRental_duation(int rental_duation) {
		this.rental_duation = rental_duation;
	}
	public int getRental_rate() {
		return rental_rate;
	}
	public void setRental_rate(int rental_rate) {
		this.rental_rate = rental_rate;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getReplacement_cost() {
		return replacement_cost;
	}
	public void setReplacement_cost(double replacement_cost) {
		this.replacement_cost = replacement_cost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecial_featues() {
		return special_featues;
	}
	public void setSpecial_featues(String special_featues) {
		this.special_featues = special_featues;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + language_id;
		result = prime * result + length;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + release_year;
		result = prime * result + rental_duation;
		result = prime * result + rental_rate;
		long temp;
		temp = Double.doubleToLongBits(replacement_cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((special_featues == null) ? 0 : special_featues.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (language_id != other.language_id)
			return false;
		if (length != other.length)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (release_year != other.release_year)
			return false;
		if (rental_duation != other.rental_duation)
			return false;
		if (rental_rate != other.rental_rate)
			return false;
		if (Double.doubleToLongBits(replacement_cost) != Double.doubleToLongBits(other.replacement_cost))
			return false;
		if (special_featues == null) {
			if (other.special_featues != null)
				return false;
		} else if (!special_featues.equals(other.special_featues))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", release_year=" + release_year
				+ ", language_id=" + language_id + ", rental_duation=" + rental_duation + ", rental_rate=" + rental_rate
				+ ", length=" + length + ", replacement_cost=" + replacement_cost + ", rating=" + rating
				+ ", special_featues=" + special_featues + "]";
	}
	public Film(int id, String title, String description, int release_year, int language_id, int rental_duation,
			int rental_rate, int length, double replacement_cost, String rating, String special_featues) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.release_year = release_year;
		this.language_id = language_id;
		this.rental_duation = rental_duation;
		this.rental_rate = rental_rate;
		this.length = length;
		this.replacement_cost = replacement_cost;
		this.rating = rating;
		this.special_featues = special_featues;
	}

	
}
