package br.com.mybudget.userdashboard.service;

import br.com.mybudget.userdashboard.model.dto.UserChartEnvelopeDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserChartService {
    UserChartEnvelopeDTO getUserDebts(Long idUser);
}