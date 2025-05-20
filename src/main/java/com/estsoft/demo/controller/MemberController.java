package com.estsoft.demo.controller;

import com.estsoft.demo.dto.MemberRequest;
import com.estsoft.demo.repository.Member;
import com.estsoft.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members")
    public List<MemberDTO> showMembers(){
        List<Member> memberList = memberService.getMemberAll();
        return memberList.stream()
                .map(member -> new MemberDTO(member))
                .toList();
    }

    @ResponseBody
    @GetMapping("/members/{id}")
    public MemberDTO showMemberById(@PathVariable("id") Long id) {
        Member member= memberService.getMemberById(id);
        return new MemberDTO(member);
    }

    @ResponseBody
    @PostMapping("/members")
    public MemberDTO addMember(@RequestBody MemberRequest member) {
        Member savedMember = memberService.insertMember(member.toEntity());
        return new MemberDTO(savedMember);
    }

    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "OK";
    }

    //GET /search/members?name={name}
    @ResponseBody
    @GetMapping("/search/members")
    public List<MemberDTO> getMemberByName(@RequestParam("name") String name) {
        List<Member> memberList = memberService.getMemberByName(name);
        return memberList.stream()
                .map(member -> new MemberDTO(member))
                .toList();
    }

}
