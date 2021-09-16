package org.zerock.mreview.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//BaseEntity : 다른 엔티티에 영향을 받지 않는 독립적으로 생성되는 엔티티.
//매핑정보만 상속받는 Superclass라는 의미의 @MappedSuperclass 어노테이션 선언.
//abstract가 붙음.

//Entity : 데이터 집합체. tuple, 속성(Column)을 가짐.
//class entity를 만들면 table이 자동으로 만들어짐.(jpa를 사용하기 때문!)

@MappedSuperclass
@EntityListeners(value={AuditingEntityListener.class})
@Getter
abstract public class BaseEntity {

    @CreatedDate
    @Column(name="regdate", updatable = false) //DB이름
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
