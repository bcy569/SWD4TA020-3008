package fi.bcy569;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.bcy569.domain_class_pojo_orm.Book;
import fi.bcy569.domain_class_pojo_orm.Category;
import fi.bcy569.domain_class_pojo_orm.interfaces.IBookRepository;
import fi.bcy569.domain_class_pojo_orm.interfaces.ICategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// CommandLineRunner suoritetaan käynnistymisen jälkeen
	// Alustukseen liittyviä toimia kuten malliksi tietokantaan olioiden
	// tallentamista
	@Bean
	public CommandLineRunner demo(IBookRepository bookRepository, ICategoryRepository categoryRepository) {
		return (args) -> {

			Category kategoria1 = new Category();
			Category kategoria2 = new Category();
			Category kategoria3 = new Category();
			
			kategoria1.setName("Atk");
			kategoria2.setName("It");
			kategoria3.setName("Ict");

			categoryRepository.save(kategoria1);
			categoryRepository.save(kategoria2);
			categoryRepository.save(kategoria3);

			Book kirja1 = new Book();
			Book kirja2 = new Book();
			Book kirja3 = new Book();

			kirja1.setAuthor("Kirjailija1");
			kirja2.setAuthor("Kirjailija2");
			kirja3.setAuthor("Kirjailija3");
			
			kirja1.setTitle("Otsikko1");
			kirja2.setTitle("Otsikko2");
			kirja3.setTitle("Otsikko3");

			kirja1.setIsbn("ISBN1");
			kirja2.setIsbn("ISBN2");
			kirja3.setIsbn("ISBN3");

			kirja1.setYear(1901);
			kirja2.setYear(1902);
			kirja3.setYear(1903);
			
			kirja1.setCategory(kategoria1);
			kirja2.setCategory(kategoria2);
			kirja3.setCategory(kategoria3);
			
			bookRepository.save(kirja1);
			bookRepository.save(kirja2);
			bookRepository.save(kirja3);
			
		};
	}

}
