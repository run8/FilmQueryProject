package com.skilldistillery.filmquery.database;

import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
  public Film findFilmById(int filmId);
  
//  With the Actor class implemented, uncomment the two commented methods in the DatabaseAccessor interface.
	public Actor findActorById(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);
}
