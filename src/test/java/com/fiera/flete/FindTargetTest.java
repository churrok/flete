package com.fiera.flete;

import com.fiera.flete.traker.adapters.ILinkRepository;
import com.fiera.flete.traker.drivers.IdGenerator;
import com.fiera.flete.traker.drivers.InMemoryLinkRepository;
import com.fiera.flete.traker.entities.LinkTrack;
import com.fiera.flete.traker.interactors.CreateLink;
import com.fiera.flete.traker.interactors.FindTarget;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindTargetTest {
    @Test
    void findTargetValidIdPassword(){
        ILinkRepository linkRepository = new InMemoryLinkRepository();
        CreateLink createLink = CreateLink.builder()
                .linkRepository(linkRepository)
                .idGenerator(new IdGenerator())
                .build();
        String url = "http://www.fierastudio.com";
        String password = "password";
        LinkTrack linkTrack = createLink.create(url,password);
        FindTarget findTarget = new FindTarget(linkRepository);
        String target = findTarget.getTarget(linkTrack.getId(),password);

        assertThat(target).isEqualTo(linkTrack.getTarget());
    }
    @Test
    void findTargetValidIdInvalidPassword(){
        ILinkRepository linkRepository = new InMemoryLinkRepository();
        CreateLink createLink = CreateLink.builder()
                .linkRepository(linkRepository)
                .idGenerator(new IdGenerator())
                .build();
        String url = "http://www.fierastudio.com";
        String password = "password";
        LinkTrack linkTrack = createLink.create(url,password);
        FindTarget findTarget = new FindTarget(linkRepository);
        String target = findTarget.getTarget(linkTrack.getId(),password + "x");

        assertThat(target).isEqualTo(null);
    }
    @Test
    void findTargetInvalidId(){
        ILinkRepository linkRepository = new InMemoryLinkRepository();
        CreateLink createLink = CreateLink.builder()
                .linkRepository(linkRepository)
                .idGenerator(new IdGenerator())
                .build();
        String url = "http://www.fierastudio.com";
        String password = "password";
        LinkTrack linkTrack = createLink.create(url,password);
        FindTarget findTarget = new FindTarget(linkRepository);


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            findTarget.getTarget("test", password);
            ;
        });
    }

}
