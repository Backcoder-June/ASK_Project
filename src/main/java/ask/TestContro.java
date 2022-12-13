package ask;

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
    JpaReposit jpaReposit;

    @Autowired
    public TestContro(TestReposit testReposit, JpaReposit jpaReposit) {
        this.testReposit = testReposit;
        this.jpaReposit = jpaReposit;
    }

    @GetMapping("/test")
    public String test2(Model model) {
        List<TestEntity> all = jpaReposit.jpafindAll();
        model.addAttribute("all", all);
        return "tester";
    }


    @GetMapping("/test/{id}")
    public String article(@PathVariable Long id, Model model) {
        TestEntity findone = jpaReposit.jpafindOne(id);
        model.addAttribute("findone", findone);
        return "detail";
    }


    // 게시판 작성
    @GetMapping("/writeboard")
    public String boardinput() {
        return "writeboard";
    }

    @PostMapping("/writeboard")
    public String getinput(BoardForm boardForm) {
        TestEntity testEntity = boardForm.toEntity();
        testReposit.save(testEntity);

        return "redirect:/test";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        jpaReposit.deleteById(id);

        return "redirect:/test";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        TestEntity testEntity = jpaReposit.jpafindOne(id);
        model.addAttribute("update", testEntity);
        return "updateBoard";
    }

    @PostMapping("/update/{id}")
    public String updateProcess(BoardForm boardForm) {
        TestEntity testEntity = boardForm.toEntity();
        System.out.println(testEntity);
        jpaReposit.jpaupdate(testEntity);
        return "redirect:/test";
    }
}



