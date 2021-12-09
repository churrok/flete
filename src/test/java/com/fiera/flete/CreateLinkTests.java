package com.fiera.flete;

import com.fiera.flete.traker.adapters.ILinkRepository;
import com.fiera.flete.traker.drivers.IdGenerator;
import com.fiera.flete.traker.drivers.InMemoryLinkRepository;
import com.fiera.flete.traker.entities.LinkTrack;
import com.fiera.flete.traker.interactors.CreateLink;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateLinkTests {

  @Test
  void createLinkFromUrl(){
    ILinkRepository linkRepository = new InMemoryLinkRepository();
    CreateLink createLink = CreateLink.builder()
        .linkRepository(linkRepository)
        .idGenerator(new IdGenerator())
        .build();
    String url = "http://www.fierastudio.com";
    String password = "password";
    LinkTrack linkTrack = createLink.create(url,password);

    assertThat(linkTrack).isNotNull();
    assertThat(linkTrack.getTarget()).isEqualTo(url);
    assertThat(linkTrack.getId()).isNotNull();
    assertThat(linkRepository.findById(linkTrack.getId())).isPresent();
    assertThat(linkTrack.getPassword()).isEqualTo(password);
  }

  @Test
  void createLinkFromUrlNullLink(){
    ILinkRepository linkRepository = new InMemoryLinkRepository();
    CreateLink createLink = CreateLink.builder()
            .linkRepository(linkRepository)
            .idGenerator(new IdGenerator())
            .build();
    String password = "password";
    LinkTrack linkTrack = createLink.create(null,password);
    assertThat(linkTrack).isNull();
  }
}
