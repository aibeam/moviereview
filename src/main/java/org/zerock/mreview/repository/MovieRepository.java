package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // N+1 문제를 해결하려면 mi에 적용되었던 max()를 걷어내고 mi만 쓴다.
//    @Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) " +
//            "from Movie m left outer join MovieImage mi on mi.movie = m " +
//            "left outer join Review r on r.movie = m group by m ")

    // 서브쿼리 사용시, 성능은 조금 포기해야하지만 max 적용 가능.
    @Query("select m, mi, avg(coalesce(r.grade,0)), count(r) " +
            " from Movie m left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review r on r.movie = m group by m ")
    Page<Object[]> getListPage(Pageable pageable);

    /*@Query("select m, mi from Movie m left outer join MovieImage mi " +
            "on mi.movie = m where m.mno =:mno ")*/
    @Query("select m, mi,avg(coalesce(r.grade,0)),count(r) " +
            "from Movie m left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m "+
            "where m.mno =:mno group by mi")
    List<Object[]> getMovieWithAll(Long mno);
}