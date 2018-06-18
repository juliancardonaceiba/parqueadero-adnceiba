package co.com.ceiba.parqueadero.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.domain.model.Registro;
import co.com.ceiba.parqueadero.domain.model.Registro_;
import co.com.ceiba.parqueadero.repository.RegistroCustomRepository;

@Service
public class RegistroCustomRepositoryImpl implements RegistroCustomRepository {

	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Registro> getRegistrosEntrada() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Registro> criteriaQuery = criteriaBuilder.createQuery(Registro.class);
		Root<Registro> rootEntity = criteriaQuery.from(Registro.class);
		rootEntity.fetch(Registro_.vehiculo);
		criteriaQuery.where(criteriaBuilder.isNull(rootEntity.get(Registro_.horaSalida)));
		criteriaQuery.orderBy(criteriaBuilder.desc(rootEntity.get(Registro_.horaIngreso)));
		TypedQuery<Registro> typedQuery = getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
