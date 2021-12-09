package com.fiera.flete.traker.entities;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class LinkTrack {
	
	@Id
	private String id;
	private String target;
	private boolean valid;
	private int usos;
	private String password;

  public LinkTrack() {
  }

  private LinkTrack(Builder builder) {
    id = builder.id;
    target = builder.target;
    valid = builder.valid;
    usos = builder.usos;
    password = builder.password;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String id;
    private String target;
    private boolean valid;
    private int usos;
    private String password;

    private Builder() {
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder target(String target) {
      this.target = target;
      return this;
    }

    public Builder valid(boolean valid) {
      this.valid = valid;
      return this;
    }

    public Builder usos(int usos) {
      this.usos = usos;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public LinkTrack build() {
      return new LinkTrack(this);
    }
  }
}
