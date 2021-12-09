package com.fiera.flete.linktrack.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fiera.flete.linktrack.dto.LinkTrackDto;
import com.fiera.flete.traker.entities.LinkTrack;
import com.fiera.flete.linktrack.repository.LinkTrackRepository;

@Service
public class LinkTrackService {
	
	@Autowired
	private LinkTrackRepository linkTrackRepository;
	
	@Value("${server.port}")
	private int port;
	
	@Value("${ambiente.url}")
	private static String local;
	
	public String getLinkRedirect(String id, String password) throws LinkTrackServiceException {
		
		LinkTrack linkTrack = this.linkTrackRepository.findById(id).get();
		
		validarLinkTrack(password, linkTrack);		
		usarLinkTrack(linkTrack);
		
		return linkTrack.getTarget();
	}

	private void usarLinkTrack(LinkTrack linkTrack) {
		
		linkTrack.setUsos(linkTrack.getUsos()+1);
		
		this.linkTrackRepository.save(linkTrack);
	}

	private void validarLinkTrack(String password, LinkTrack linkTrack) throws LinkTrackServiceException {
		
		if(!linkTrack.isValid()) throw new LinkTrackServiceException("Link invalido");
		
		if(!password.equals(linkTrack.getPassword())) throw new LinkTrackServiceException("Password invalido");
	}
	
	public LinkTrackDto crearLinkTrack(String url, String password) throws UnknownHostException {
		
		LinkTrack linkTrack = cargarLinkTrack(url, password);
		
		linkTrack = this.linkTrackRepository.save(linkTrack);
		
		return entityToDto(linkTrack);
	}

	private LinkTrack cargarLinkTrack(String url, String password) {
		
		LinkTrack linkTrack = new LinkTrack();
		
		linkTrack.setTarget(url);
		linkTrack.setValid(true);
		linkTrack.setUsos(0);
		linkTrack.setPassword(password);
		
		return linkTrack;
	}

	private LinkTrackDto entityToDto(LinkTrack linkTrack) throws UnknownHostException {
		
		LinkTrackDto linkTrackDto =new LinkTrackDto();
		
		linkTrackDto.setTarget(linkTrack.getTarget());
		linkTrackDto.setUrl(getHostAddress() + ":" + port +"/" + linkTrack.getId());
		linkTrackDto.setValid(linkTrack.isValid());
		linkTrackDto.setUsos(linkTrack.getUsos());
		linkTrackDto.setPassword(linkTrack.getPassword());
		
		return linkTrackDto;
	}

	private String getHostAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	public LinkTrackDto invalidarLinkTrack(String id) throws UnknownHostException {
		
		LinkTrack linkTrack = this.linkTrackRepository.findById(id).get();
		
		linkTrack.setValid(false);
		linkTrack = this.linkTrackRepository.save(linkTrack);
		
		return entityToDto(linkTrack);
	}

	public LinkTrackDto getLinkTrack(String id) throws UnknownHostException {
		
		LinkTrack linkTrack = this.linkTrackRepository.findById(id).get();
				
		return entityToDto(linkTrack);		
	}
	
	
}
