package com.ohgiraffers.section06.compositekey.subsection01.embedded;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "member_section06_subsection01")
@Table(name = "tbl_member_section06_subsection01")
public class Member {

    //복합 키가 아닌 경우 @Id를 사용했었으나, 별도의 member PK타입의 클래스 생성
    @EmbeddedId //복합 키 매핑 시 어노테이션 작성 2
    private MemberPK memberPK;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    public Member() {
    }

    public Member(MemberPK memberPK, String phone, String address) {
        this.memberPK = memberPK;
        this.phone = phone;
        this.address = address;
    }

    public MemberPK getMemberPK() {
        return memberPK;
    }

    public void setMemberPK(MemberPK memberPK) {
        this.memberPK = memberPK;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberPK=" + memberPK +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
