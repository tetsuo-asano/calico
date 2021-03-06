package calicosample.entity;

import java.time.LocalDateTime;

import calicosample.core.doma.InjectConfig;
import jp.co.freemind.calico.core.auth.AuthInfo;
import jp.co.freemind.calico.core.zone.Zone;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

/**
 * Created by tasuku on 15/03/10.
 */
@Entity(listener = CalicoSampleEntity.CalicoSampleEntityListener.class)
@InjectConfig
@Getter @Setter
public abstract class CalicoSampleEntity {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;

  public static class CalicoSampleEntityListener<T extends CalicoSampleEntity> implements EntityListener<T> {
    @Override public void preInsert(T entity, PreInsertContext<T> context) {
      entity.setCreatedAt(LocalDateTime.now());
      entity.setCreatedBy(getLoginId());
    }
    @Override public void preUpdate(T entity, PreUpdateContext<T> context) {
      entity.setUpdatedAt(LocalDateTime.now());
      entity.setUpdatedBy(getLoginId());
    }

    private String getLoginId() {
      return Zone.getContext().getAuthInfo()
        .map(AuthInfo::getLoginId)
        .orElse("");
    }
  }
}
