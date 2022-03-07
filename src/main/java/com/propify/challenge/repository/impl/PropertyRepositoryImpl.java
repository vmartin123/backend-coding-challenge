package com.propify.challenge.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.propify.challenge.model.Property;
import com.propify.challenge.repository.PropertyRepositoryCustom;

@Repository
public class PropertyRepositoryImpl implements PropertyRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<Property> search(String minRentPrice, String maxRentPrice) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Property> cq = cb.createQuery(Property.class);
		Root<Property> property = cq.from(Property.class);

		List<Predicate> predicates = new ArrayList<>();

		if (minRentPrice != null) {
			predicates.add(cb.ge(property.get("rentPrice"), Double.parseDouble(minRentPrice)));
		}

		if (maxRentPrice != null) {
			predicates.add(cb.le(property.get("rentPrice"), Double.parseDouble(maxRentPrice)));
		}

		cq.where(predicates.toArray(new Predicate[0]));

		return new HashSet<Property>(em.createQuery(cq).getResultList());
	}

}
