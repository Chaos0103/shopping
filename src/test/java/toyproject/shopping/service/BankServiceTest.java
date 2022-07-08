package toyproject.shopping.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shopping.domain.Bank;
import toyproject.shopping.domain.Member;
import toyproject.shopping.exception.DuplicateException;
import toyproject.shopping.repository.BankRepository;
import toyproject.shopping.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BankServiceTest {

    @Autowired BankService bankService;
    @Autowired BankRepository bankRepository;
    @Autowired MemberRepository memberRepository;

    @Test
    void createBank() {
        Member member = getMember();
        Long bankId = bankService.create(member.getId(), 1000000);

        Bank findBank = bankRepository.findById(bankId).get();

        assertThat(findBank).isNotNull();
    }

    @Test
    void duplicateBank() {
        Member member = getMember();
        bankService.create(member.getId(), 1000000);

        assertThrows(DuplicateException.class, () -> {
            bankService.create(member.getId(), 1000000);
        });
    }

    @Test
    void deposit() {
        Member member = getMember();
        Long bankId = bankService.create(member.getId(), 1000000);
        Bank findBank = bankRepository.findById(bankId).get();

        int balance = bankService.deposit(findBank.getAccount(), 100000);

        assertThat(findBank.getMoney()).isEqualTo(balance);
    }

    @Test
    void withdrawal() {
        Member member = getMember();
        Long bankId = bankService.create(member.getId(), 1000000);
        Bank findBank = bankRepository.findById(bankId).get();

        int balance = bankService.withdrawal(findBank.getAccount(), 100000);

        assertThat(findBank.getMoney()).isEqualTo(balance);
    }

    @Test
    void withdrawalException() {
        Member member = getMember();
        Long bankId = bankService.create(member.getId(), 10000);
        Bank findBank = bankRepository.findById(bankId).get();

        assertThrows(IllegalStateException.class, () -> {
            bankService.withdrawal(findBank.getAccount(), 20000);
        });
        assertThat(findBank.getMoney()).isEqualTo(10000);
    }

    private Member getMember() {
        return memberRepository.save(new Member("testMember@naver.com", "test1!", "member", "01011111111"));
    }
}