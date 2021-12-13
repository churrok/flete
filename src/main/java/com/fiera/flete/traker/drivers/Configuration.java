package com.fiera.flete.traker.drivers;

import com.fiera.flete.traker.adapters.IIdGenerator;
import com.fiera.flete.traker.adapters.ILinkRepository;
import com.fiera.flete.traker.interactors.CreateLink;
import com.fiera.flete.traker.interactors.FindTarget;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
  @Bean
  public CreateLink createLink(ILinkRepository linkRepository, IIdGenerator idGenerator){
    return CreateLink.builder()
        .idGenerator(idGenerator)
        .linkRepository(linkRepository)
        .build();
  }
  @Bean
  public FindTarget findTarget(ILinkRepository linkRepository){
    return FindTarget.builder()
            .linkRepository(linkRepository)
            .build();
  }

}
