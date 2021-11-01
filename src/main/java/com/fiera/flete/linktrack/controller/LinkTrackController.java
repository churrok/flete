package com.fiera.flete.linktrack.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fiera.flete.linktrack.dto.LinkTrackDto;
import com.fiera.flete.linktrack.service.LinkTrackService;




@RestController
public class LinkTrackController {

	@Autowired
	private LinkTrackService linkTrackService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@Param(value = "url")String url,@Param(value = "password")String password) {
		
		try {
			LinkTrackDto ret = this.linkTrackService.crearLinkTrack(url,password);
			return new ResponseEntity<LinkTrackDto>(ret, HttpStatus.CREATED);
		} catch (Exception e) {
			return (ResponseEntity<?>) ResponseEntity.noContent();
		}
        
    }
	
	@RequestMapping(path = "/{idLinkTrack}", method = RequestMethod.GET)
	public void redirect(@PathVariable(value = "idLinkTrack") String id,@Param(value = "password")String password,HttpServletResponse httpServletResponse) {
		
		try {			
			String url = this.linkTrackService.getLinkRedirect(id,password);
			
			httpServletResponse.setHeader("Location", url);
		    httpServletResponse.setStatus(302);
		    
		} catch (Exception e) {
			httpServletResponse.setStatus(404);
		}
	}
	
	@RequestMapping(value = "/{idLinkTrack}", method = RequestMethod.PUT)
	public ResponseEntity<?> invalidar(@PathVariable(value = "idLinkTrack") String id){
		
		try {
			LinkTrackDto linkTrackDto = this.linkTrackService.invalidarLinkTrack(id);
			return new ResponseEntity<LinkTrackDto>(linkTrackDto, HttpStatus.ACCEPTED);
		}
		catch(Exception e){
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}
	}
	
	@RequestMapping(value = "/estadistica/{idLinkTrack}", method = RequestMethod.GET)
	public ResponseEntity<?> estadistica(@PathVariable(value = "idLinkTrack") String id){
		try {
			LinkTrackDto linkTrackDto = this.linkTrackService.getLinkTrack(id);
			return new ResponseEntity<LinkTrackDto>(linkTrackDto, HttpStatus.ACCEPTED);
		}
		catch(Exception e){
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}
	}
	
	
	
}
