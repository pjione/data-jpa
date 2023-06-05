package study.datajpa.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }
}
