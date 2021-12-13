package com.fiera.flete.traker.drivers;

import com.fiera.flete.traker.adapters.ILinkRepository;
import com.fiera.flete.traker.entities.LinkTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

public class MongoLinkRepositoryEmbedded implements ILinkRepository {


    private final MongoTemplate mongoTemplate;

    public MongoLinkRepositoryEmbedded(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public LinkTrack save(LinkTrack linkTrack) {
        return mongoTemplate.save(linkTrack);
    }

    @Override
    public Optional<LinkTrack> findById(String trackId) {
        LinkTrack ret =  mongoTemplate.findById(trackId,LinkTrack.class);
        return Optional.ofNullable(ret);
    }
}
