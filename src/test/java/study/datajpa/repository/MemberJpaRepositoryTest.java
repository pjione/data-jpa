package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    private MemberJpaRepository memberJpaRepository;
    @Test
    void save() {
        Member member = new Member("userA");
        member.setUsername("userA");
        Long saveId = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(saveId);
        assertThat(findMember.getId()).isEqualTo(saveId);
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

}