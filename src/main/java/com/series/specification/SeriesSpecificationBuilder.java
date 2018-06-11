package com.series.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.series.model.Series;
import com.series.web.util.SearchCriterion;

public class SeriesSpecificationBuilder {

	private final List<SearchCriterion> searchCriteria;
	
	public SeriesSpecificationBuilder() {
		searchCriteria = new ArrayList<SearchCriterion>();
	}
	
	public SeriesSpecificationBuilder with(String key, String operation, Object value) {
		searchCriteria.add(new SearchCriterion(key, operation, value));
		return this;
	}
	
	public Specification<Series> build() {
		if (searchCriteria.size() == 0) {
			return null;
		}
		
		List<Specification<Series>> specs = new ArrayList<Specification<Series>>();
		for (SearchCriterion criterion : searchCriteria) {
			specs.add(new SeriesSpecification(criterion));
		}
		
		Specification<Series> result = specs.get(0);
		for (int i = 1; i < specs.size(); i++) {
			result = Specification.where(result).and(specs.get(i));
		}
		return result;
	}
}
