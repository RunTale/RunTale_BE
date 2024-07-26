package com.likelion.runtale.domain.scenario.controller;

import com.likelion.runtale.domain.scenario.entity.Scenario;
import com.likelion.runtale.domain.scenario.entity.ScenarioStep;
import com.likelion.runtale.domain.scenario.service.ScenarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "scenario", description = "scenario 관련 API")
@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    @Autowired
    private ScenarioService scenarioService;

    @Operation(summary = "시나리오 조회")
    @GetMapping("/{scenarioId}")
    public ResponseEntity<Scenario> getScenario(@PathVariable Long scenarioId) {
        Scenario scenario = scenarioService.getScenario(scenarioId);
        return ResponseEntity.ok(scenario);
    }

    @Operation(summary = "시나리오 단계 조회")
    @GetMapping("/{scenarioId}/step/{stepNumber}")
    public ResponseEntity<ScenarioStep> getScenarioStep(@PathVariable Long scenarioId, @PathVariable int stepNumber) {
        ScenarioStep scenarioStep = scenarioService.getScenarioStep(scenarioId, stepNumber);
        return ResponseEntity.ok(scenarioStep);
    }
}
