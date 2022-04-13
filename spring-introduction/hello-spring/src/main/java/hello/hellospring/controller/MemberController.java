package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // 스프링이 관리를 할 떄는 컨테이너에 등록하고 받아서 쓸 수 있도록 바꿔야 한다.
    // 만약 그렇게 하지 않으면 여러 컨트롤러가 사용할 수 있게 된다.

    private final MemberService memberService;

    // Autowired 가 생성자에 있으면 스프링이 MemberService 와 연결시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



}
