package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class TodoEntity {
    @Id
    @GeneratedValue(generator = "system-uuid") // id를 자동으로 생성하겠다는 뜻, 이때 generator로 어떤 방식으로 id를 생성할지 지정할 수 있다.
    @GenericGenerator(name = "system-uuid", strategy = "uuid") // system-uuid는 @GenericGenerator에 정의된 generator의 이름으로 @GenericGenerator는 Hibernate가 제공하는 기본 Generator가 아니라 나만의 Generator를 사용하고 싶을 경우 이용한다.
    private String id;
    private String userId;
    private String title;
    private boolean done; // true - todo를 완료한 경우(checked)
}
