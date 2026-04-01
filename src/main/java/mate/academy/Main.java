package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    public static void main(String[] args) {

        CinemaHallService cinemaHallService =
                (CinemaHallService) Injector.getInstance("mate.academy");
        CinemaHall hall = new CinemaHall();
        hall.setCapacity(100);
        hall.setDescription("Blue hall");
        hall = cinemaHallService.add(hall);

        MovieService movieService =
                (MovieService) Injector.getInstance("mate.academy");
        Movie movie = movieService.add(new Movie("Batman"));

        MovieSession session = new MovieSession();
        session.setMovie(movie);
        session.setCinemaHall(hall);
        session.setShowTime(LocalDateTime.now());

        MovieSessionService movieSessionService =
                (MovieSessionService) Injector.getInstance("mate.academy");

        movieSessionService.add(session);

        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);
    }
}
