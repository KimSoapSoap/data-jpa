package org.example.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.example.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
       Member member = new Member("memberA");
       Member saveMember = memberJpaRepository.save(member);

       //when
       Member findMember = memberJpaRepository.find(saveMember.getId());

       //then
       assertThat(findMember.getId()).isEqualTo(saveMember.getId());
       assertThat(findMember.getUsername()).isEqualTo(saveMember.getUsername());
   }

}