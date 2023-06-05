package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {
    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }
    public Member find(Long id){
        return em.find(Member.class, id);
    }


}
