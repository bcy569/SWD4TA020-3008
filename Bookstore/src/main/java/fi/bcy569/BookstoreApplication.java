package fi.bcy569;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.bcy569.domain_class_pojo_orm.Book;
import fi.bcy569.domain_class_pojo_orm.interfaces.IBookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// CommandLineRunner suoritetaan käynnistymisen jälkeen
	// Alustukseen liittyviä toimia kuten malliksi tietokantaan olioiden
	// tallentamista
	@Bean
	public CommandLineRunner demo(IBookRepository bookRepository) {
		return (args) -> {

//		Book kirja1 = new Book("Eemelin seikkailut", "Kirjailijan Nimi 1", "ISBN 123", 1900, 40);
			Book kirja1 = new Book();
			Book kirja2 = new Book();
			Book kirja3 = new Book();

			kirja1.setTitle("Otsikko1");
			kirja2.setTitle("Otsikko2");
			kirja3.setTitle("Otsikko3");

			bookRepository.save(kirja1);
			bookRepository.save(kirja2);
			bookRepository.save(kirja3);

		};
	}

}
