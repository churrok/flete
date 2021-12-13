package com.fiera.flete.traker.interactors;

import com.fiera.flete.traker.adapters.IIdGenerator;
import com.fiera.flete.traker.adapters.ILinkRepository;
import com.fiera.flete.traker.entities.LinkTrack;

import java.util.UUID;

public class CreateLink {
  private final ILinkRepository linkRepository;
  private final IIdGenerator idGenerator;

  private CreateLink(Builder builder) {
    linkRepository = builder.linkRepository;
    idGenerator = builder.idGenerator;
  }

  public static Builder builder() {
    return new Builder();
  }

  public LinkTrack create(String url,String password) {
    if(url == null || password == null){
      return null;
    }
    LinkTrack link = LinkTrack.builder()
        .id(idGenerator.generate())
        .valid(true).usos(0)
        .target(url).password(password).build();
    return linkRepository.save(link);
  }

  public static class Builder {
    private ILinkRepository linkRepository;
    private IIdGenerator idGenerator;

    private Builder() {
    }

    public Builder linkRepository(ILinkRepository linkRepository) {
      this.linkRepository = linkRepository;
      return this;
    }

    public Builder idGenerator(IIdGenerator idGenerator) {
      this.idGenerator = idGenerator;
      return this;
    }

    public CreateLink build() {
      return new CreateLink(this);
    }
  }
}
