package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 안 된다
        // Optional 로 감싸서 옵셔널 안에 멤버 객체가 있다. 옵셔널에 있는 메소드를 사용한다.
        // Optional 은 Null 이 있는 경우에 사용하면 된다.ㅎ

        // 아래의 코드는 예쁘지가 않다. 어차피 Optional 로 감싸여 있을 것이기에 바로 체이닝
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // result.ifPresent((m) -> {
        //     throw new IllegalStateException("이미 존재하는 회원입니다.");
        // });

        // Ctrl + T 를 사용해서 메소드화 함
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    // Ctrl + T 를 하면 리팩터링과 관련된 내용이 나온다
    // 자주 사용하는 내용은 현재 만든 로직을 메소드로 추출하는 것 (Extract Method + 커맨드-옵션-M)
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
    * 전체 회원 조회
    */

    // 레포지토리는 저장소에 넣었다 뺐다 하는 느낌
    // 그러나 서비스 클래스는 Join, FindMembers 와 같이 비즈니스와 관련된 내용이 들어감
    // 그렇기에 서비스는 비즈니스 언어와 비슷한 말로, 비즈니스에 의존적으로 작성이 돼야 한다.
    public List<Member> findMembers () {
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

