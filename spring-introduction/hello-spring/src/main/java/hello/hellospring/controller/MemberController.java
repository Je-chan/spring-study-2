package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 스프링이 관리를 할 떄는 컨테이너에 등록하고 받아서 쓸 수 있도록 바꿔야 한다.
    // 만약 그렇게 하지 않으면 여러 컨트롤러가 사용할 수 있게 된다.

    private final MemberService memberService;

    // Autowired 가 생성자에 있으면 스프링이 MemberService 와 연결시켜준다.
    // MemberService 에 가면 순수 자바 클래스라 스프링이 알 방법이 없다. Anotation 이 없기 때문.
    // 이럴 때는 MemberService 에 가서 @Service 를 넣어주면 된다.
    // 그리고 MemoryMemberRepository 에 가서 @Repository 를 붙인다.
    // Controller(외부 요청 받음), Service(비즈니스 로직), Repository(데이터 저장) 가 굉장히 정형화된 패
    @Autowired
    // Autowired 를 사용해서 MemberController 가 생성이 될 때, 스프링 빈에 등록된 Members 객체를 가져다 넣어준다
    // 이게 Dependency Injection.
    // @Autowired 로 MemberService 와 연관을 짓는다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public  String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
       List<Member> members = memberService.findMembers();
       model.addAttribute("members", members);
       return "members/memberList";
    }

}
