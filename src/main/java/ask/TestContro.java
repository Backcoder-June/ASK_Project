package ask;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class TestContro {

    TestReposit testReposit;
    @Autowired
    public TestContro(TestReposit testReposit) {
        this.testReposit = testReposit;
    }

    @GetMapping("/test")
    public String test2(Model model) {

        List<TestEntity> all = testReposit.findAll();

        model.addAttribute("all", all);

        return "tester";
    }


    @GetMapping("/test/{id}")
    public String article(@PathVariable Long id, Model model) {

        Optional<TestEntity> byId = testReposit.findById(id);

        model.addAttribute("FindById", byId);

        return "tester";
    }


    // 게시판 작성
    @GetMapping("/writeboard")
    public String boardinput(){
        return "writeboard";}

    @PostMapping("/writeboard")
    public String getinput(BoardForm boardForm){
        TestEntity testEntity = boardForm.toEntity();
        testReposit.save(testEntity);

        return "redirect:/test"; }




}



