package com.ohgiraffers.section03.primarykey.subsection01.sequence;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimaryKeyMappingTests {

    //application 당 1개만
    private static EntityManagerFactory entityManagerFactory;

    //스레드 세이프 하지 않고, 요청 당 1개
    private EntityManager entityManager;

    @BeforeAll //junit에서 오는 어노테이션, 테스트가 진행되기 전에 한 번 진행된다.
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach //테스트 하나가 진행되기 전에 한 번씩
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll //테스트가 끝나기 전에 한 번만
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach //테스트가 끝날 떄마다 한 번씩
    public void closeManager() {
        entityManager.close();
    }

    @Test
    public void 식별자_매핑_테스트() {

        // given
        Member member1 = new Member();
        //member.setMemberNo(1); 제거 이유 : 시퀀스를 이용해 값을 자동 생성을 하기 위해서
        member1.setMemberId("user01");
        member1.setMemberPwd("pass01");
        member1.setNickname("홍길동");
        member1.setPhone("010-1234-5678");
        member1.setAddress("서울시 종로구");
        member1.setEnrollDate(new Date());
        member1.setMemberRole("ROLE_MEMBER");
        member1.setStatus("Y");
        Member member2 = new Member();
        member2.setMemberId("user02");
        member2.setMemberPwd("pass02");
        member2.setNickname("황주희");
        member2.setPhone("010-9876-5432");
        member2.setAddress("서울시 강북구");
        member2.setEnrollDate(new Date());
        member2.setMemberRole("ROLE_MEMBER");
        member2.setStatus("Y");
        //=> 비영속 객체 생성

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member1);
        entityManager.persist(member2);
        //=>영속 객체로 만들기 위해
        entityTransaction.commit();
        //실제로 commit

        // then
        //JPQL : MySQL 구문이나 ORACLE 구문을 직접 작성하는 것을 native SQL이라고 하고, JPQL은 SQL처럼 생겼으나 JPA에서 생성하는 구문(방언이 있는 것이 좋음 = 알아서 DB 구문 작성)
        String jpql = "SELECT A.memberNo FROM member_section03_subsection01 A";
        //sql 구문 구조이나 차이점은 테이블명과 컬럼명이 아닌 필드명과 엔티티명을 작성, 별칭을 무조건 작성
        List<Integer> memberNoList = entityManager.createQuery(jpql, Integer.class).getResultList();
        memberNoList.forEach(System.out::println);

    }

}
