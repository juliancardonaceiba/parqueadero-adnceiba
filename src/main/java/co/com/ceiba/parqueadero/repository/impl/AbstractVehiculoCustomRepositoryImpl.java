package co.com.ceiba.parqueadero.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;

import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Registro_;
import co.com.ceiba.parqueadero.domain.model.Vehiculo;
import co.com.ceiba.parqueadero.domain.model.Vehiculo_;
import co.com.ceiba.parqueadero.repository.AbstractVehiculoCustomRepository;

public abstract class AbstractVehiculoCustomRepositoryImpl<T extends Vehiculo> implements AbstractVehiculoCustomRepository<T> {

	private EntityManager entityManager;

	private Class<T> entityClass;

	public AbstractVehiculoCustomRepositoryImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Long getCantidadVehiculosRegistrados() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
		Root<? extends Vehiculo> root = criteriaQuery.from(getEntityClass());
		ListJoin<? extends Vehiculo, Registro> joinRegistros = root.join(Vehiculo_.registros);
		criteriaQuery.where(criteriaBuilder.isNull(joinRegistros.get(Registro_.horaSalida)));
		criteriaQuery.multiselect(criteriaBuilder.count(root.get(Vehiculo_.id)));
		TypedQuery<?> query = getEntityManager().createQuery(criteriaQuery);
		return (Long) query.getSingleResult();
	}

}
