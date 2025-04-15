package com.estsoft.demo.service;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //Get all member info
    public List<Member> getMemberAll() {
        return memberRepository.findAll();
    }

    //Get member by id
    public Member getMemberById(Long id) {
        Optional<Member> optMember =  memberRepository.findById(id);
        return optMember.orElseGet(Member::new);
    }

    //Save member info
    public Member insertMember(Member member) {
        return memberRepository.save(member);
    }

    //Delete member info
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> getMemberByName(String name) {
        return memberRepository.findByNameContaining(name);
    }

}
