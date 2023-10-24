package com.ohgiraffers.section06.compositekey.subsection01.embedded;

import org.junit.jupiter.api.*;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmbeddedKeyTests {

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
    public void 임베디드_아이디_사용한_복합키_테이블_매핑_테스트() {

        // given
        Member member = new Member();
        member.setMemberPK(new MemberPK(1, "user01"));
        member.setPhone("010-1234-5678");
        member.setAddress("서울시 종로구");

        // when
        //transaction commit하면서 저장
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member); //비영속 객체를 영속 객체로 만들고
        entityTransaction.commit(); //commit

        // then
        Member foundMember = entityManager.find(Member.class, member.getMemberPK());
        assertEquals(member.getMemberPK(), foundMember.getMemberPK());

    }

}
