package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    void testMember(){
        Member member = new Member("userA");
        member.setUsername("userA");
        Member save = memberRepository.save(member);
        Member findMember = memberRepository.findById(save.getId()).orElseThrow();
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }
    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deleted = memberRepository.count();
        assertThat(deleted).isEqualTo(0);

    }
    @Test
    void paging(){
        //given
        Member member1 = memberRepository.save(new Member("member1", 10));
        Member member2 = memberRepository.save(new Member("member2", 10));
        Member member3 = memberRepository.save(new Member("member3", 10));
        Member member4 = memberRepository.save(new Member("member4", 10));
        Member member5 = memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(1, 3, Sort.by(Sort.Direction.DESC, "username"));

        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        Slice<Member> slice = memberRepository.findSliceByAge(age, pageRequest);

        //then
        List<Member> content = page.getContent();
        List<Member> content2 = slice.getContent();
        long totalElements = page.getTotalElements();

        assertThat(content.size()).isEqualTo(2);
        assertThat(totalElements).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(1);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isFalse();
        assertThat(page.hasNext()).isFalse();

        assertThat(content2.size()).isEqualTo(2);
        for (Member member : content2) {
            System.out.println("asdf : " + member);
        }
        assertThat(slice.getNumber()).isEqualTo(1);
        assertThat(slice.isFirst()).isFalse();
        assertThat(slice.hasNext()).isFalse();
        /*int age = 10;
        int offset = 0;
        int limit = 3;
*/
        //when
        /*List<Member> members = memberRepository.findByAge(age, offset, limit);
        long totalCount = memberRepository.totalCount(age);*/
    }
}