package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {
    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }
    public void delete(Member member){
        em.remove(member);
    }
    public List<Member> findAll(){
        return em.createQuery("select m from Member m ", Member.class).getResultList();
    }
    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    public long count(){
        return em.createQuery("select count(m) from Member m", Long.class).getSingleResult();
    }
    public Member find(Long id){
        return em.find(Member.class, id);
    }


}
