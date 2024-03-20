import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        while (true) {
            runMenu();
            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
            while (input != 1 && input != 2 && input != 3) {
                input = in.nextInt();
            }
            if (input == 1) {
                try {
                    movieSearch();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (input == 2) {
                actorSearch();
            }
            else if (input == 3) {
                break;
            }
        }
    }
    public static void runMenu() {
        System.out.println("what do u want?\n1-movies and series\n2-actors\n3-exit");
    }

    public static void movieSearch() throws IOException {
        while (true) {
            System.out.println("what's the name of the movie or series you're looking for?");
            Scanner theMovie = new Scanner(System.in);
            String movieName = theMovie.nextLine();

            Movie movie = new Movie(new ArrayList<>(), "", 0);
            String moviesInfoJson = movie.getMovieData(movieName);
            String movieCheck = movie.movieErrorHandling(moviesInfoJson);
            if (movieCheck.equals("True")) {
                System.out.println("\nTitle: " + movieName);
                System.out.println("Year: " + movie.getYearViaApi(moviesInfoJson));
                System.out.println("Imdb Votes: " + movie.getImdbVotesViaApi(moviesInfoJson));
                System.out.println("Director: " + movie.getDirectorViaApi(moviesInfoJson));
                System.out.println("Actors: " + movie.getActorListViaApi(moviesInfoJson));
                System.out.println("Genre: " + movie.getGenreViaApi(moviesInfoJson));
                System.out.println("Released: " + movie.getReleaseViaApi(moviesInfoJson));
                System.out.println("Rating -> Internet Movie Database : " + movie.getRatingViaApi(moviesInfoJson));
                System.out.println("\n\n\nPress q to go back to the menu and c to continue searching!");
                String input = theMovie.nextLine();
                if (input.equals("q")) {
                    break;
                }
                else {
                    continue;
                }
            }
            else {
                System.out.println("Movie not found!\n");
            }
        }
    }

    public static void actorSearch() {
        while (true) {
            System.out.println("what's the name of the actor you're looking for?");
            Scanner theActor = new Scanner(System.in);
            String actorName = theActor.nextLine();

            Actors actor = new Actors(0, false);
            String actorsInfoJson = actor.getActorData(actorName);
            boolean actorCheck = actor.actorErrorHandling(actorsInfoJson);
            if (actorCheck) {
                System.out.println("\nName: " + actorName);
                System.out.println("Gender: " + actor.getGenderViaAPi(actorsInfoJson));
                System.out.println("Nationality: " + actor.getNationalityViaApi(actorsInfoJson));
                System.out.println("Birthday: " + actor.getBirthdayViaApi(actorsInfoJson));
                System.out.println("Age: " + actor.getAgeViaAPi(actorsInfoJson));
                System.out.println("is Alive: " + actor.isAlive(actorsInfoJson));
                System.out.println("Date of death: " + actor.getDateOfDeathViaApi(actorsInfoJson));
                System.out.println("Height: " + actor.getHeightViaAPi(actorsInfoJson));
                System.out.println("Net worth: " + actor.getNetWorthViaApi(actorsInfoJson));
                System.out.println("\n\n\nPress q to go back to the menu and c to continue searching!");
                String input = theActor.nextLine();
                if (input.equals("q")) {
                    break;
                }
                else {
                    continue;
                }
            }
            else {
                System.out.println("You entered the wrong name!\n");
            }
        }
    }
}