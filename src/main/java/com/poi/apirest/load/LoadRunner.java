package com.poi.apirest.load;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.poi.apirest.models.PointOfInterest;
import com.poi.apirest.repository.PointOfInterestRepository;

@Component
public class LoadRunner implements ApplicationRunner {

	@Autowired
	private PointOfInterestRepository pointOfInterestRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("... Initizaling with preload data");

		List<PointOfInterest> listOfPOIs = Arrays.asList(
				new PointOfInterest(27, 12, "Lanchonete"),
				new PointOfInterest(31, 18, "Posto"),
				new PointOfInterest(15, 12, "Joalheria"),
				new PointOfInterest(19, 21, "Floricultura"),
				new PointOfInterest(23, 6, "Supermercado"),
				new PointOfInterest(12, 8, "Pub"),
				new PointOfInterest(28, 2, "Churrascaria")
		);

		pointOfInterestRepository.saveAll(listOfPOIs);
	}

}
