package com.skilldistillery.filmquery.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
//	Implement the findFilmById method that takes an int film ID, 
//	and returns a Film object (or null, if the film ID returns no data.)

	@Override
	public Film findFilmById(int filmId) {
		String sql = "SELECT film.id, first_name, last_name FROM actor WHER id = ?";
		PreparedStatement stmt = null;
		stmt = conn.prepareStatements(sql);
		stmt.setInt(1, filmId);
		return null;
	}

//	Implement findActorById method that takes an int actor ID, 
//	and returns an Actor object (or null, if the actor ID returns no data.)
	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		// ...
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt(1));
			actor.setFirstName(actorResult.getString(2));
			actor.setLastName(actorResult.getString(3));
			actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
		}
		// ...
		return actor;
	}
//	Implement findActorsByFilmId with an appropriate List implementation that 
//	will be populated using a ResultSet and returned.
//	Make sure your JDBC code uses PreparedStatement with 
//	bind variables instead of concatenating values into SQL strings.

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}

// Use JDBC Test as an example.
// This is the only class that will access the DB.