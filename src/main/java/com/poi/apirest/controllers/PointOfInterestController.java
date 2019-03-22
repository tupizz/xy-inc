package com.poi.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poi.apirest.models.PointOfInterest;
import com.poi.apirest.repository.PointOfInterestRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest XY-Inc")
@CrossOrigin(origins = "*")
public class PointOfInterestController {

	@Autowired
	private PointOfInterestRepository pointOfInterestRepository;

	@GetMapping("/poi")
	@ApiOperation(value = "Lista todos os pois")
	public ResponseEntity<List<PointOfInterest>> listAllPOIS() {
		List<PointOfInterest> listOfAllPOI = this.pointOfInterestRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listOfAllPOI);
	}
	
	@GetMapping("/poi-nearby") 
	@ApiOperation(value = "Lista todos os pois dentro de uma distância máxima fornecida")
	public ResponseEntity<List<PointOfInterest>> listPOIsWithNearby(@RequestParam Integer x, @RequestParam Integer y, @RequestParam Integer d) {
		List<PointOfInterest> listOfAllPOI = this.pointOfInterestRepository.findNearbyPOIs(x, y, d);
		return ResponseEntity.status(HttpStatus.OK).body(listOfAllPOI);
	}

	@PostMapping("/poi")
	@ApiOperation(value = "Salva um poi")
	public ResponseEntity<PointOfInterest> saveNewPOI(@RequestBody PointOfInterest poi) {
		PointOfInterest savedPOI = this.pointOfInterestRepository.save(poi);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPOI);
	}
}
