package com.fiera.flete.traker.adapters;

import com.fiera.flete.traker.entities.LinkTrack;

import java.util.Optional;

public interface ILinkRepository {
  LinkTrack save(LinkTrack linkTrack);

  Optional<LinkTrack> findById(String trackId);
}
