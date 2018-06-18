package co.com.ceiba.parqueadero.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import co.com.ceiba.parqueadero.domain.model.Recargo;
import co.com.ceiba.parqueadero.domain.model.Recargo_;
import co.com.ceiba.parqueadero.repository.RecargoCustomRepository;

public class RecargoCustomRepositoryImpl implements RecargoCustomRepository {

	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Recargo> getRecargoParaCilindraje(Integer cilidrajeMinimo) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Recargo> criteriaQuery = criteriaBuilder.createQuery(Recargo.class);
		Root<Recargo> rootEntity = criteriaQuery.from(Recargo.class);
		criteriaQuery.where(criteriaBuilder.le(rootEntity.get(Recargo_.cilindrajeMinimo), cilidrajeMinimo));
		criteriaQuery.orderBy(criteriaBuilder.desc(rootEntity.get(Recargo_.cilindrajeMinimo)));
		TypedQuery<Recargo> query = getEntityManager().createQuery(criteriaQuery);
		query.setMaxResults(1);
		List<Recargo> resultList = query.getResultList();
		return resultList.isEmpty() ? Optional.empty() : Optional.ofNullable(resultList.get(0));
	}

}
