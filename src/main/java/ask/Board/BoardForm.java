package ask.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardForm {

    private Long id;
    private String title;
    private String contents;
    private String userid;
    private String category;
    private String donation;
    private String asktime;
    private String createdat;


    public BoardDTO toEntity() {
        return new BoardDTO(id, title, contents, userid, category, donation, asktime, createdat);
    }






}