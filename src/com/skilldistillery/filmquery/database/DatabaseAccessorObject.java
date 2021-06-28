package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	@Override
	public Film findFilmById(int filmId) {
		String user = "student";
		String pass = "student";
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.*, language.name \n" + "FROM film JOIN language\n"
					+ "          ON film.language_id = language.id\n" + "          WHERE film.id = ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("film.id");
				String title = rs.getString("film.title");
				String description = rs.getString("film.description");
				int release_year = rs.getInt("film.release_year");
				int language_id = rs.getInt("film.language_id");
				int rental_duration = rs.getInt("film.rental_duration");
				double rental_rate = rs.getDouble("film.rental_rate");
				int length = rs.getInt("film.length");
				double replacement_cost = rs.getDouble("film.replacement_cost");
				String rating = rs.getString("film.rating");
				String special_features = rs.getString("film.special_features");
				String language = rs.getString("name");

				film = new Film(id, title, description, release_year, language_id, rental_duration, rental_rate, length,
						replacement_cost, rating, special_features, language);
				film.setCast(findActorsByFilmId(filmId));

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String user = "student";
		String pass = "student";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor;\n" + "WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();

			int id = rs.getInt("actor.id");
			String first_name = rs.getString("actor.fist_name");
			String last_name = rs.getString("actor.last_name");
			actor = new Actor(id, first_name, last_name);

			if (rs.next()) {
				rs.close();
				stmt.close();
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<Actor>();
		String user = "student";
		String pass = "student";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name, film.title\n"
					+ "FROM actor JOIN film_actor \n" + " ON actor.id = film_actor.actor_id\n" + " JOIN film\n"
					+ " ON film.id = film_actor.film_id\n" + " WHERE film.id = ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			if (rs.equals(null)) {
				return null;
			}

			while (rs.next()) {
				Actor actor = new Actor(rs.getInt("actor.id"), rs.getString("actor.first_name"),
						rs.getString("actor.last_name"));
				actors.add(actor);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<Film>();
		String user = "student";
		String pass = "student";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT film.*, language.name " + "FROM film JOIN language ON film.language_id = language.id "
					+ "WHERE (film.title LIKE ? OR film.description LIKE ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();

			if (rs.equals(null)) {
//				System.out.println("Inside if null");
				return null;
			}

			while (rs.next()) {
				Film film = new Film();
				film.setTitle(rs.getString("film.title"));
				film.setDescription(rs.getString("film.description"));
				film.setRelease_year(rs.getInt("film.release_year"));
				film.setRating(rs.getString("film.rating"));
				film.setLanguage(rs.getString("name"));
				film.setCast(findActorsByFilmId(rs.getInt("film.id")));
				films.add(film);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for (Film film : films) {
//			System.out.println(film.getTitle());
//		}
		return films;
	}
}