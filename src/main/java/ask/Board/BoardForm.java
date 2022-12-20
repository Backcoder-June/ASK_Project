package ask.Board;
public class BoardForm {

    private Long id;
    private String title;
    private String contents;

    public BoardForm(){}
    public BoardForm(Long id, String title, String contents) {
        this.id = id;
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




    public BoardDTO toEntity() {
        return new BoardDTO(id, title, contents);
    }






}