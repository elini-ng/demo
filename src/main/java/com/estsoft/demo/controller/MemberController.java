package com.estsoft.demo.controller;

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
    public Member showMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @ResponseBody
    @PostMapping("/members")
    public Member addMember(@RequestBody Member member) {
        return memberService.insertMember(member);
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
    public List<Member> getMemberByName(@RequestParam("name") String name) {
        return memberService.getMemberByName(name);
    }

}
