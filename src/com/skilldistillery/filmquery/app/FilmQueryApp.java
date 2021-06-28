package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
		System.out.println("Goodbye");
	}

//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) {
		int selection = 0;
		while (selection != 1 && selection != 2 && selection != 3) {

			try {

				System.out.println("/////////////////////////////////////");
				System.out.println("Select a below option and presse enter:");
				System.out.println("1. Look up film by ID");
				System.out.println("2. Look up film by a search keyword");
				System.out.println("3. Exit the application");

				selection = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter 1, 2 or 3.");
				input.nextLine();

			}

			switch (selection) {
			case 1:
				searchByID(input);
				startUserInterface(input);
				break;
			case 2:
				searchByKeyWord(input);
				startUserInterface(input);
			default:
				break;
			}
		}
	}

	private void searchByKeyWord(Scanner scanner) {
		System.out.println("Enter keyword: ");
		String keyword = "";

		do {
			try {
				keyword = scanner.next();
			} catch (InputMismatchException e) {
				System.out.println("You must enter a word for keyword.");
			}
			scanner.nextLine();
		} while (keyword.equals(""));

		List<Film> films = db.findFilmsByKeyword(keyword);

		if (!(films.isEmpty())) {
			for (Film film : films) {
				System.out.println("//////////////////////////");
				System.out.println(film.getTitle());
				System.out.println(film.getDescription());
				System.out.println(film.getRelease_year());
				System.out.println(film.getRating());
				System.out.println(film.getLanguage());
				printCast(film.getCast());
				System.out.println("//////////////////////////");
			}
		} else {
			System.out.println("//////////////////////////");
			System.out.println("No film matches that keyword.");
			System.out.println("//////////////////////////");
		}
	}

	private void searchByID(Scanner scanner) {
		Film film;
		int filmID = 0;

		while (filmID == 0) {
			try {
				scanner.nextLine();

				System.out.print("Enter the film ID: ");
				filmID = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter a number for film ID.");
			}
		}

		film = db.findFilmById(filmID);

		if (!(film == null)) {
			System.out.println("//////////////////////////");
			System.out.println("Title: " + film.getTitle());
			System.out.println("Description: " + film.getDescription());
			System.out.println("Release year: " + film.getRelease_year());
			System.out.println("Rating: " + film.getRating());
			System.out.println("Language: " + film.getLanguage());
			printCast(film.getCast());

			System.out.println("//////////////////////////");
		} else {
			System.out.println("//////////////////////////");
			System.out.println("No film matches that film ID.");
			System.out.println("//////////////////////////");
		}
	}

	private void printCast(List<Actor> cast) {
		if (cast.isEmpty()) {
			System.out.println("There is no cast in this film.");
		}
		System.out.println("Cast:");
		for (Actor actor : cast) {
			System.out.println("First Name: " + actor.getFirst_name());
			System.out.println("Last name: " + actor.getLast_name());
		}
		System.out.println("//////////////////////////");
	}
}