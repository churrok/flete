package com.fiera.flete.traker.interactors;

import com.fiera.flete.traker.adapters.ILinkRepository;
import com.fiera.flete.traker.entities.LinkTrack;

public class FindTarget {

    private ILinkRepository linkRepository;

    public FindTarget(ILinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    private FindTarget(Builder builder) {
        linkRepository = builder.linkRepository;
    }

    public String getTarget(String id, String password) {
        LinkTrack linkTrack;
        linkTrack = linkRepository.findById(id).get();
        if(linkTrack.getPassword().equals(password)){
            return linkTrack.getTarget();
        }
        return null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ILinkRepository linkRepository;

        public Builder() {
        }

        public Builder linkRepository(ILinkRepository val) {
            linkRepository = val;
            return this;
        }

        public FindTarget build() {
            return new FindTarget(this);
        }
    }
}
