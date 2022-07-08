package toyproject.shopping.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import toyproject.shopping.domain.Member;
import toyproject.shopping.domain.QMember;
import toyproject.shopping.repository.custom.MemberRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.Optional;

import static toyproject.shopping.domain.QMember.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Member> memberValidation(String email, String phone) {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(
                        emailEq(email).or(phoneEq(phone))
                )
                .fetchOne();
        return Optional.ofNullable(findMember);
    }

    private BooleanExpression emailEq(String email) {
        return email != null ? member.email.eq(email) : null;
    }

    private BooleanExpression phoneEq(String phone) {
        return phone != null ? member.phone.eq(phone) : null;
    }
}
