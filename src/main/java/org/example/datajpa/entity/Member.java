package org.example.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;


    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null) {
            this.team = team;
        }
    }

    //원칙적으로 구 list에 있는 team을 제거하도록 코드를 작성하는 것이 맞다.
    //다만 이 관계에서 list는 연관관계의 주인이 아니므로 실제 데이터베이스에 영향을 주지는 않는다.
    //변하는 것은 Member의 외래키인 team_id만 변경될 뿐이므로 db의 무결성은 문제가 없다.
    //mappedBy로 조회만 하니까 당장 트랜잭션 내부에서? 재사용 할 것이 아니라면
    //다시 조회할 때는 변경된 사항이 조회될 듯
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
