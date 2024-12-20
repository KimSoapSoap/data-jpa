package org.example.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.example.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberJpaRepositoryTest {

   @Autowired
    MemberJpaRepository memberJpaRepository;

   @Test
   @Transactional
   public void member_test() throws Exception {
       //given
       Member member1 = new Member("member1");
       Member member2 = new Member("member2");
       memberJpaRepository.save(member1);
       memberJpaRepository.save(member2);

       //when
       Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
       Member findMember2 = memberJpaRepository.findById(member2.getId()).get();


       //then
       //단건 조회 검증
       assertThat(findMember1.getId()).isEqualTo(member1.getId());
       assertThat(findMember2.getId()).isEqualTo(member2.getId());


       //리스트 조회 검증
       List<Member> all = memberJpaRepository.findAll();
       assertThat(all.size()).isEqualTo(2);

       //카운트 검증
       long count = memberJpaRepository.count();
       assertThat(count).isEqualTo(2);

       //삭제 검증
       memberJpaRepository.delete(member1);
       memberJpaRepository.delete(member2);

       long deletedCount = memberJpaRepository.count();
       assertThat(deletedCount).isEqualTo(0);



   }

}