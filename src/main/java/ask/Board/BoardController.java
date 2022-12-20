package ask.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/allboard")
    public String test2(Model model) {
        List<BoardDTO> all = boardRepository.jpafindAll();
        model.addAttribute("all", all);
        return "board/allBoard";
    }


    @GetMapping("/oneboard/{id}")
    public String article(@PathVariable Long id, Model model) {
        BoardDTO findone = boardRepository.jpafindOne(id);
        model.addAttribute("findone", findone);
        return "board/oneBoard";
    }


    // 게시판 작성
    @GetMapping("/writeboard")
    public String boardinput() {
        return "board/writeBoard";
    }

    @PostMapping("/writeboard")
    public String getinput(BoardForm boardForm) {
        BoardDTO boardDTO = boardForm.toEntity();
        boardRepository.save(boardDTO);

        return "redirect:/allboard";
    }

    @GetMapping("/deleteboard/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardRepository.deleteById(id);

        return "redirect:/allboard";
    }

    @GetMapping("/updateboard/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTO = boardRepository.jpafindOne(id);
        model.addAttribute("update", boardDTO);
        return "board/updateBoard";
    }

    @PostMapping("/updateboard/{id}")
    public String updateProcess(BoardForm boardForm) {
        BoardDTO boardDTO = boardForm.toEntity();
        boardRepository.jpaupdate(boardDTO);
        return "redirect:/allboard";
    }
}



