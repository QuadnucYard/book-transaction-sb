package team.wuse.koob.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "book")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * Title of the book.
	 */
	private String title;

	/**
	 * Author name.
	 */
	private String author;

	/**
	 * Publication date.
	 */
	private Date date;

	/**
	 * Publisher.
	 */
	private String publisher;

	/**
	 * Abstract of the book.
	 */
	private String abs;

	/**
	 * The url of the book's cover.
	 */
	private String cover;

}