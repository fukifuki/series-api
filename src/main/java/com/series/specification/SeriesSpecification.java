package com.series.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.series.model.Series;
import com.series.web.util.SearchCriterion;

public class SeriesSpecification implements Specification<Series>{

	SearchCriterion criterion;
	
	public SeriesSpecification(final SearchCriterion criterion) {
//		TODO Why super() is called here?
//		super();
		this.criterion = criterion;
	}
	
	public SearchCriterion getCriterion() {
		return criterion;
	}
	
	@Override
	public Predicate toPredicate(Root<Series> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (criterion.getOperation().equalsIgnoreCase(":")) {
			if (root.get(criterion.getKey()).getJavaType() == String.class) {
				return criteriaBuilder.like(
					root.<String>get(criterion.getKey()), "%" + criterion.getValue() + "%");		
			}
		}
		return null;
	}
	
}
