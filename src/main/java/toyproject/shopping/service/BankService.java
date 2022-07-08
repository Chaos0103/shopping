package toyproject.shopping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shopping.domain.Bank;
import toyproject.shopping.domain.Member;
import toyproject.shopping.exception.DuplicateException;
import toyproject.shopping.exception.NoSuchException;
import toyproject.shopping.repository.BankRepository;
import toyproject.shopping.repository.MemberRepository;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final MemberRepository memberRepository;

    /**
     * 은행개설
     */
    public Long create(Long memberId, int money) {
        duplicate(memberId);
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalStateException("없음");
        });
        Bank savedBank = bankRepository.save(new Bank(findMember, getRandom(13), getRandom(16), money));
        return savedBank.getId();
    }

    /**
     * 입금
     */
    public int deposit(String account, int money) {
        Bank findBank = bankRepository.findByAccount(account)
                .orElseThrow(() -> {
                    throw new NoSuchException();
                });
        findBank.deposit(money);
        return findBank.getMoney();
    }

    /**
     * 출금
     */
    public int withdrawal(String account, int money) {
        Bank findBank = bankRepository.findByAccount(account)
                .orElseThrow(() -> {
                    throw new NoSuchException();
                });
        findBank.withdrawal(money);
        return findBank.getMoney();
    }

    private void duplicate(Long memberId) {
        Optional<Bank> findBank = bankRepository.findByMemberId(memberId);
        if (findBank.isPresent()) {
            throw new DuplicateException("같은 명의로 개설된 통장이 있습니다.");
        }
    }

    private String getRandom(int count) {
        Random rand = new Random();
        String number = "";
        for (int i = 0; i < count; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            number += ran;
        }
        return number;
    }
}
