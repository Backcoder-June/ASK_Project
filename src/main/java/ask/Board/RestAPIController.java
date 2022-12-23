package ask.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestAPIController {

    BoardRepository boardRepository;

    @Autowired
    public RestAPIController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Autowired


    @GetMapping("/api/hello")
    public String hello(){
        return "hello restapi";
    }

    @GetMapping("/api/allboard")
    public List<BoardDTO> boardindex(){
        return boardRepository.jpafindAll();
    }

    @PostMapping("/api/write")
    public BoardDTO create(@RequestBody BoardForm dto){
        BoardDTO boardDTO = dto.toEntity();
        return boardRepository.save(boardDTO);
    }


}
