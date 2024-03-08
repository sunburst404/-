package com.lyc.springboot.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
@Getter
@Setter
  public class Longid implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  private String description;


}
