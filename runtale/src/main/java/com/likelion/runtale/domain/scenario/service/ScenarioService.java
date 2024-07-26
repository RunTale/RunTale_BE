package com.likelion.runtale.domain.scenario.service;

import com.likelion.runtale.common.exception.NotFoundException;
import com.likelion.runtale.common.response.ErrorMessage;
import com.likelion.runtale.domain.scenario.entity.Scenario;
import com.likelion.runtale.domain.scenario.entity.ScenarioStep;
import com.likelion.runtale.domain.scenario.repository.ScenarioRepository;
import com.likelion.runtale.domain.scenario.repository.ScenarioStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScenarioService {

    @Autowired
    private ScenarioRepository scenarioRepository;

    @Autowired
    private ScenarioStepRepository scenarioStepRepository;

    public Scenario getScenario(Long scenarioId) {
        return scenarioRepository.findById(scenarioId).orElseThrow(() -> new NotFoundException(ErrorMessage.SCENARIO_NOT_FOUND));
    }

    public ScenarioStep getScenarioStep(Long scenarioId, int stepNumber) {
        Optional<ScenarioStep> scenarioStep = scenarioStepRepository.findByScenarioIdAndStepNumber(scenarioId, stepNumber);
        return scenarioStep.orElseThrow(() -> new NotFoundException(ErrorMessage.SCENARIO_STEP_NOT_FOUND));
    }
}
