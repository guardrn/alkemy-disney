package alkemy.disney.repository.specification;

import alkemy.disney.dto.MovieFilterDTO;
import alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFilterDTO filterDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(filterDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filterDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }
            if (filterDTO.getGenre() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("genre"), filterDTO.getGenre())
                );
            }
            query.distinct(true);
            String orderByField = "title";
            query.orderBy(filterDTO.isASC() ?
                    criteriaBuilder.asc(root.get(orderByField)) :
                    criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
