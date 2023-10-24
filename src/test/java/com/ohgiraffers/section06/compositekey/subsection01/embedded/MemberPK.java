package com.ohgiraffers.section06.compositekey.subsection01.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//복합 키에 대한 대응 클래스
// 복합 키 클래스는 반드시 직렬화 처리, equals & hashcode 오버라이딩
//복합 키 사용 시 MemberPK 클래스 위에 어노테이션 생성
//복합 키를 mapping하는 방법 1
//@Embeddable 작성 후 Member 클래스에 @EmbeddedId 작성하는 방법
@Embeddable //복합 키 매핑 시 어노테이션 작성 1
public class MemberPK implements Serializable {
    @Column(name="member_no")
    private int memberNo;
    @Column(name="member_id")
    private String memberId;

    public MemberPK() {
    }

    public MemberPK(int memberNo, String memberId) {
        this.memberNo = memberNo;
        this.memberId = memberId;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberPK)) return false;
        MemberPK memberPK = (MemberPK) o;
        return memberNo == memberPK.memberNo && Objects.equals(memberId, memberPK.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, memberId);
    }

    @Override
    public String toString() {
        return "MemberPK{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
