package com.api.parkingcontrol.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import com.api.parkingcontrol.controllers.ParkingSpotController;
import com.api.parkingcontrol.models.CarModel;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import com.api.parkingcontrol.services.CarService;
import com.api.parkingcontrol.services.ParkingSpotService;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import ch.qos.logback.core.net.ObjectWriter;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ParkingSpotController.class)
public class ParkingSpotControllerTest {

    @MockBean
    private ParkingSpotService parkingSpotService;

    @MockBean
    private CarService carService;

    @MockBean
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private MockMvc mockMvc;

    UUID id1 = UUID.randomUUID();

    @Test
    public void testparkingControllerGetByBlock() throws Exception {

        UUID id1 = UUID.randomUUID();
        ParkingSpotModel parking1 = new ParkingSpotModel(id1, "300C", LocalDateTime.now(ZoneId.of("UTC")), "Renan",
                "200", "B", null);
        ParkingSpotModel parking2 = new ParkingSpotModel(UUID.randomUUID(), "300C", LocalDateTime.now(ZoneId.of("UTC")),
                "Renan", "200", "B", null);

        Mockito.when(parkingSpotService.findByBlock("B")).thenReturn(asList(parking1, parking2));

        mockMvc.perform(get("/parking-spot/block/B"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(id1.toString())))
                .andExpect(jsonPath("$[0].parkingSpotNumber", Matchers.is("300C")))
                .andExpect(jsonPath("$[0].responsibleName", Matchers.is("Renan")))
                .andExpect(jsonPath("$[0].apartment", Matchers.is("200")))
                .andExpect(jsonPath("$[0].block", Matchers.is("B")));
    }

    @Test
    public void testparkingControllerGetById() throws Exception {

        UUID id1 = UUID.fromString("3090f679-a57e-4b5e-8c4a-c3b70f0756c6");
        ParkingSpotModel parking1 = new ParkingSpotModel(id1, "300C", LocalDateTime.now(ZoneId.of("UTC")), "Renan",
                "200", "B", null);

        Mockito.when(parkingSpotService.findById(id1)).thenReturn(Optional.of(parking1));

        mockMvc.perform(get("/parking-spot/" + id1.toString()))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(7)))
                .andExpect(jsonPath("$.id", Matchers.is(id1.toString())))
                .andExpect(jsonPath("$.parkingSpotNumber", Matchers.is("300C")))
                .andExpect(jsonPath("$.responsibleName", Matchers.is("Renan")))
                .andExpect(jsonPath("$.apartment", Matchers.is("200")))
                .andExpect(jsonPath("$.block", Matchers.is("B")));
    }

    // doNothing().when(mock).someVoidMethod();
    @Test
    public void testparkingControllerDeleteId() throws Exception {

        ParkingSpotModel parking1 = new ParkingSpotModel(id1, "300C", LocalDateTime.now(ZoneId.of("UTC")), "Renan",
                "200", "B", null);

        // Mockito.when(parkingSpotService.findById(id1)).thenReturn(Optional.of(parking1)).the‌​nReturn(null);
        // Mockito.doReturn("Parking Spot not
        // found.").when(parkingSpotService).deleteByIdParkingSpot(parking1);
        // Como delete é um void método, as opções acima não funcionam
        Mockito.when(parkingSpotService.save(parking1)).thenReturn(parking1);
        doThrow(IllegalArgumentException.class).when(parkingSpotService).deleteByIdParkingSpot(parking1);

        mockMvc.perform(delete("/parking-spot/" + id1))
                .andExpect(status().is(404));
    }

    @Test
    public void testParkingSpotPost() throws Exception {

        // ParkingSpotModel parking1 = new
        // ParkingSpotModel(UUID.randomUUID(),"300A",LocalDateTime.now(ZoneId.of("UTC")),"Renan","205","B",null);
        CarModel carModel = new CarModel(1, "ABC1010", "fiat", "uno", "preto", null);
        UUID id1 = UUID.fromString("3090f679-a57e-4b5e-8c4a-c3b70f0756c6");
        ParkingSpotModel parking1 = ParkingSpotModel.builder()
                .id(id1)
                .parkingSpotNumber("300A")
                .registrationDate(null)
                .responsibleName("Renan")
                .apartment("205")
                .block("B")
                .carModel(carModel)
                .build();

        Mockito.when(parkingSpotService.save(parking1)).thenReturn(parking1);

        String content = "{\"id\":\"3090f679-a57e-4b5e-8c4a-c3b70f0756c6\",\"parkingSpotNumber\":\"300A\",\"licensePlateCar\":\"RRS701\",\"brandCar\":\"fiat\",\"modelCar\":\"q5\",\"colorCar\":\"black\",\"responsibleName\":\"Renan Rodolfo\",\"apartment\" :\"210\",\"block\": \"2\"}";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/parking-spot")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", Matchers.is(id1.toString())))
                .andExpect(jsonPath("$.parkingSpotNumber", Matchers.is("300A")))
                .andExpect(jsonPath("$.responsibleName", Matchers.is("Renan Rodolfo")))
                .andExpect(jsonPath("$.apartment", Matchers.is("210")))
                .andExpect(jsonPath("$.block", Matchers.is("2")));
    }
}