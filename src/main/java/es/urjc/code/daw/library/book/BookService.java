package es.urjc.code.daw.library.book;

import java.util.List;
import java.util.Optional;

import es.urjc.code.daw.library.Features;
import org.springframework.stereotype.Service;

import es.urjc.code.daw.library.notification.NotificationService;
import org.togglz.core.manager.FeatureManager;

/* Este servicio se usará para incluir la funcionalidad que sea 
 * usada desde el BookRestController y el BookWebController
 */
@Service
public class BookService {

	private static final int LINE_LENGTH = 10;

	private BookRepository repository;
	private NotificationService notificationService;

	private LineBreaker lineBreaker;

	private FeatureManager featureManager;

	public BookService(BookRepository repository, NotificationService notificationService, LineBreaker lineBreaker, FeatureManager featureManager){
		this.repository = repository;
		this.notificationService = notificationService;
		this.lineBreaker = lineBreaker;
		this.featureManager = featureManager;
	}

	public Optional<Book> findOne(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Book> findAll() {
		return repository.findAll();
	}

	public Book save(Book book) {
		Book newBook = book;

		if (this.featureManager.isActive(Features.FEATURE_LINE_BREAKER)) {
			newBook.setDescription(lineBreaker.breakLine(book.getDescription(), LINE_LENGTH));
		}
		newBook = repository.save(newBook);
		notificationService.notify("Book Event: book with title=" + newBook.getTitle() + " was created");
		return newBook;
	}

	public void delete(long id) {
		repository.deleteById(id);
		notificationService.notify("Book Event: book with id="+id+" was deleted");
	}
}
