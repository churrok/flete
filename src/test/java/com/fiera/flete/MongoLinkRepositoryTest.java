package com.fiera.flete;

import com.fiera.flete.linktrack.repository.LinkTrackRepository;
import com.fiera.flete.traker.adapters.ILinkRepository;
import com.fiera.flete.traker.drivers.IdGenerator;
import com.fiera.flete.traker.drivers.InMemoryLinkRepository;
import com.fiera.flete.traker.drivers.MongoLinkRepository;
import com.fiera.flete.traker.drivers.MongoLinkRepositoryEmbedded;
import com.fiera.flete.traker.entities.LinkTrack;
import com.fiera.flete.traker.interactors.CreateLink;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MongoLinkRepositoryTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;

        ImmutableMongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
    }

    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")
    @Test
    void test() throws Exception {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        // when
        mongoTemplate.save(objectToSave, "collection");

        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
                .containsOnly("value");
    }

    @Test
    void MongoLinkRepositorySave(){
        ILinkRepository linkRepository = new MongoLinkRepositoryEmbedded(mongoTemplate);
        CreateLink createLink = CreateLink.builder()
                .linkRepository(linkRepository)
                .idGenerator(new IdGenerator())
                .build();
        String url = "http://www.fierastudio.com";
        String password = "password";
        LinkTrack linkTrack = LinkTrack.builder().password(password)
                .target(url).usos(0).valid(true).build();
        linkTrack = createLink.create(url,password);

        assertThat(linkTrack).isNotNull();
        assertThat(linkTrack.getTarget()).isEqualTo(url);
        assertThat(linkTrack.getId()).isNotNull();
        assertThat(linkTrack.getPassword()).isEqualTo(password);
    }

}
