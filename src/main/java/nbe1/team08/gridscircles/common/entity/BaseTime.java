package nbe1.team08.gridscircles.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass   // Entity 클래스가 BaseTime 을 상속받을 때, createdDate, modifiedDate 를 인식할 수 있도록 하는 설정입니다.
@EntityListeners(AuditingEntityListener.class)  // 자동으로 값을 넣어주도록 하는 annotation 입니다.
@Getter
public abstract class BaseTime {

    @CreatedDate    // 데이터 생성할 때 시간 자동 생성
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate   // 데이터 수정할 때 시간 자동 수정
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}