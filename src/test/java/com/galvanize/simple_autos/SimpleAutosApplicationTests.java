package com.galvanize.simple_autos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleAutosApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    AutoRepository autoRepository;

    Random r = new Random();
    List<Automobiles> testAutos;
    @BeforeEach
    void setup(){
        this.testAutos = new ArrayList<>();
        String[] colors = {"RED", "BLUE", "GREEN", "ORANGE", "YELLOW", "BLACK", "BROWN", "ROOT BEER", "MAGENTA", "AMBER"};
        for (int i = 0; i < 50; i++) {
            Automobiles auto;
            if (i % 3 == 0) {
                auto = new Automobiles(1967, "Ford", "Mustang", "AABBCC" + (i * 13));
            } else if (i % 2 == 0) {
                auto = new Automobiles(2000, "Dodge", "Viper", "VVBBXX" + (i * 12));
            } else {
                auto = new Automobiles(2020, "Audi", "Quatro", "QQZZAA" + (i * 12));
            }
            auto.setColor(colors[r.nextInt(colors.length)]);
            this.testAutos.add(auto);
        }
        autoRepository.saveAll(this.testAutos);
    }

    @AfterEach
    void tearDown(){
        autoRepository.deleteAll();
    }
	@Test
	void contextLoads() {
	}

    @Test
    void getAutos_exists_returnsAutosList() {//GET: /api/autos with no parms
        ResponseEntity<AutoList> response = restTemplate.getForEntity("/api/autos", AutoList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isEmpty()).isFalse();
        for (Automobiles auto : response.getBody().getAutomobiles()){
            System.out.println(auto);
        }}

        @Test
        void getAutos_search_returnsAutosList() {//GET: /api/autos with color & Make
            int seq = r.nextInt(50);
            String color = testAutos.get(seq).getColor();
            String make = testAutos.get(seq).getMake();
            ResponseEntity<AutoList> response = restTemplate.getForEntity(
                    String.format("/api/autos?color=%s&make=%s", color, make), AutoList.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().isEmpty()).isFalse();
            assertThat(response.getBody().getAutomobiles().size()).isGreaterThanOrEqualTo(1);
            for (Automobiles auto : response.getBody().getAutomobiles()) {
                System.out.println(auto);
            }
        }

    @Test
    void addAuto_returnsNewAutoDetails() {//POST: /api/autos
        Automobiles automobile = new Automobiles(1995, "Ford", "Windstar", "ABC123XX");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Automobiles> request = new HttpEntity<>(automobile, headers);

        ResponseEntity<Automobiles> response = restTemplate.postForEntity("/api/autos", request, Automobiles.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getVin()).isEqualTo(automobile.getVin());
    }


}
