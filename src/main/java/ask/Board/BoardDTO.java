package ask.Board;
import javax.persistence.*;

@Entity
@Table(name = "BOARD")
public class BoardDTO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    public BoardDTO(){}

    public BoardDTO(Long ID, String title, String contents) {
        this.id = ID;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }


    @Override
    public String toString() {
        return "BoardEntity{" +
                "ID=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}