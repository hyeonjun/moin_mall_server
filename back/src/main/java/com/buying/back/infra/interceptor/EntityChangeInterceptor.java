package com.buying.back.infra.interceptor;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.persistence.Entity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EntityChangeInterceptor extends EmptyInterceptor {

  private final Logger logger = LoggerFactory.getLogger(EntityChangeInterceptor.class);
  private final StringBuilder sb = new StringBuilder();

  @Override
  public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
    if (isEntityObjects(entity)) {
      auditChanges(entity, null, state, propertyNames);
    }
    return super.onSave(entity, id, state, propertyNames, types);
  }

  @Override
  public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
    String[] propertyNames, Type[] types) {
    if (isEntityObjects(entity)) {
      auditChanges(entity, previousState, currentState, propertyNames);
    }
    return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
  }

  private void auditChanges(Object entity, Object[] previousState, Object[] currentState, String[] propertyNames) {
    sb.append("Entity Name: ").append(entity.getClass().getSimpleName());
    if (previousState == null) {
      sb.append(" Insert State=[ ");
      auditInsertEntity(currentState, propertyNames);
    } else {
      sb.append(" Update State=[ ");
      auditUpdateEntity(previousState, currentState, propertyNames);
    }
    sb.deleteCharAt(sb.length() - 2).append("]");
    logger.info(sb.toString());
    sb.setLength(0);
  }

  private void auditInsertEntity(Object[] currentState, String[] propertyNames) {
    for (int i = 0; i < propertyNames.length; i++) {
      Object currentValue = getValue(currentState, i);
      if (currentValue != null) {
        String propertyName = propertyNames[i];
        if (isEntityObjects(currentValue)) {
          propertyName += "Id";
          currentValue = getEntityId(currentValue);
        }
        sb.append(propertyName).append(":").append(currentValue).append(", ");
      }
    }
  }

  private void auditUpdateEntity(Object[] previousState, Object[] currentState, String[] propertyNames) {
    for (int i = 0; i < propertyNames.length; i++) {
      Object previousValue = getValue(previousState, i);
      Object currentValue = getValue(currentState, i);
      if (isChanged(previousValue, currentValue)) {
        String propertyName = propertyNames[i];
        if (isEntityObjects(previousValue) || isEntityObjects(currentValue)) {
          propertyName += "Id";
          previousValue = getEntityId(previousValue);
          currentValue = getEntityId(currentValue);
        }
        sb.append(propertyName).append(":").append(previousValue).append("->").append(currentValue).append(", ");
      }
    }
  }

  private Object getValue(Object[] state, int index) {
    return (state != null && index < state.length) ? state[index] : null;
  }

  private boolean isChanged(Object previousValue, Object currentValue) {
    return (previousValue == null && currentValue != null) || (previousValue != null && !previousValue.equals(currentValue));
  }

  private boolean isEntityObjects(Object value) {
    return value != null && value.getClass().getAnnotation(Entity.class) != null;
  }

  private Object getEntityId(Object entity) {
    if (entity == null) {
      return null;
    }

    try {
      Method getIdMethod = entity.getClass().getMethod("getId");
      return getIdMethod.invoke(entity);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      logger.error(e.getMessage());
    }

    return null;
  }
}
