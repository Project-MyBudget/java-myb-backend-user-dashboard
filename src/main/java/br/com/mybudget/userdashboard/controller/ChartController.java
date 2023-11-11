package br.com.mybudget.userdashboard.controller;

import br.com.mybudget.userdashboard.model.dto.UserChartEnvelopeDTO;
import br.com.mybudget.userdashboard.service.UserChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartController {

    @Autowired
    private UserChartService userChartService;

    @GetMapping(value = "/charts/{idUser}")
    public ResponseEntity<UserChartEnvelopeDTO> getChartsDashboard(@PathVariable long idUser) {
        UserChartEnvelopeDTO userChartEnvelopeDTO = userChartService.getUserDebts(idUser);
        return ResponseEntity.ok(userChartEnvelopeDTO);
    }

}
