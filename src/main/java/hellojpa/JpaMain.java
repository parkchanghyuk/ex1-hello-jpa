package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			// studyJpaStart(em); // JPA 시작하기
			// studyEntityManager(em, tx);
			studyEntityMapping(em);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

	private static void studyEntityMapping(EntityManager em) {
		Member2 member = new Member2();
		member.setUsername("C");
		System.out.println(">>>>>>>>>>>>>>>>>>");
		em.persist(member);
		System.out.println(">>>>>>>>>>>>>>>>>>");
	}

	/**
	 * 영속성 컨텍스트
	 */
	private static void studyEntityManager(EntityManager em, EntityTransaction tx) {
		// commit_시점_확인(em);
		// 일차_캐시_테스트(em);
		// 일차_캐시_테스트2(em);
		// 쓰기_지연(em);
		// 엔티티_변경_감지(em);
		// flush(em);
		// 준영속_변경(em);
	}

	private static void 준영속_변경(EntityManager em) {
		// 준영속 상태가 되면 commit 되어도 아무일도 일어나지 않는다.
		Member member = em.find(Member.class,150L);
		member.setUsername("asdfsaf");
		// 준영속 상태로 변경하는 방법
		em.detach(member); // member 를 준영속상태로 변경
		em.clear(); // entity manager 초기화
		em.close(); // entity manager close
		System.out.println(">>>>>>>>>>>>>");
	}

	private static void flush(EntityManager em) {
		// flush 실행 시 1차 캐시의 값은 유지하고 쿼리를 바로 실행한다.
		// flush : 기본 auto(커밋이나 쿼리 실행 시 flush), 영속성 컨택스트를 비우지 않는다. 트랜젝션 단위로 이루어짐
		Member member = new Member(200L, "membert200");
		em.persist(member);
		em.flush();
		System.out.println(">>>>>>>>>>");
	}

	private static void 엔티티_변경_감지(EntityManager em) {
		// 최초 시점을 스냅샷으로 저장. 그 값과 비교하여 변경이 이뤄지면 persist 안해도 자동으로 update 된다.
		Member member = em.find(Member.class,150L);
		member.setUsername("zzzzz");

		System.out.println(">>>>>>>>>");
	}

	private static void 쓰기_지연(EntityManager em) {
		// 커밋 이후에 쿼리가 한방에 나감
		Member member1 = new Member(150L, "A");
		Member member2 = new Member(160L, "B");

		em.persist(member1);
		em.persist(member2);

		System.out.println(">>>>>>>>>>>>>>>>>");
	}

	private static void 일차_캐시_테스트2(EntityManager em) {
		// 일차캐시를 통해 쿼리가 두번 날라가지 않음
		Member findMember = em.find(Member.class, 101L);
		Member findMember2 = em.find(Member.class, 101L);
	}

	private static void 일차_캐시_테스트(EntityManager em) {
		// 값을 저장하지 않고 일차 캐시에 저장된 내용을 반환함
		// 비영속
		Member member = new Member();
		member.setId(101L);
		member.setUsername("Hello jpa");
		// 영속
		System.out.println(">>>>>> before");
		em.persist(member);
		System.out.println(">>>>>> after");

		Member findMember = em.find(Member.class, 101L);

		System.out.println("findMember.getId() = " + findMember.getId());
		System.out.println("findMember.getName() = " + findMember.getUsername());
	}

	private static void commit_시점_확인(EntityManager em) {
		// 비영속
		Member member = new Member();
		member.setId(100L);
		member.setUsername("Hello jpa");

		// 영속
		System.out.println(">>>>>> before");
		em.persist(member);
		System.out.println(">>>>>> after");
	}

	/**
	 * JPA start
	 */
	private static void studyJpaStart(EntityManager em) {
		// hellojpa.Member findMember = em.find(hellojpa.Member.class, 1L);
		// System.out.println("findMember = " + findMember.getName());
		List<Member> result = em.createQuery("select m from Member as m", Member.class)
			.getResultList();

		for (Member member : result) {
			System.out.println("member.name = " + member.getUsername());
		}
	}
}
