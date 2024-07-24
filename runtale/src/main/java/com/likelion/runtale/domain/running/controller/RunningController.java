package com.likelion.runtale.domain.running.controller;

import com.likelion.runtale.common.ApiResponse;
import com.likelion.runtale.common.exception.UnauthorizedException;
import com.likelion.runtale.common.response.ErrorMessage;
import com.likelion.runtale.common.response.SuccessMessage;
import com.likelion.runtale.domain.running.dto.RunningRequest;
import com.likelion.runtale.domain.running.dto.RunningResponse;
import com.likelion.runtale.domain.running.entity.Running;
import com.likelion.runtale.domain.running.service.RunningService;
import com.likelion.runtale.domain.user.entity.User;
import com.likelion.runtale.domain.user.service.UserService;
import com.likelion.runtale.web.SessionConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Tag(name = "running", description = "running 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/running")
public class RunningController {
    private final RunningService runningService;

    @Operation(summary = "러닝 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<RunningResponse>> createRunning(
            @SessionAttribute(SessionConst.LOGIN_USER) User loginUser,
            @RequestBody RunningRequest runningRequest) {
        if (loginUser == null) {
            throw new UnauthorizedException(ErrorMessage.USER_NOT_AUTHORIZED);
        }
        RunningResponse response = runningService.saveRunning(loginUser.getId(), runningRequest);
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.RUNNING_INFO_SUCCESS,response));
    }

    @Operation(summary = "유저의 러닝 기록 조회")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<RunningResponse>>> getRunningsByUserId(@PathVariable Long userId) {
        List<Running> runnings = runningService.getRunningsByUserId(userId);
        List<RunningResponse> response = runnings.stream()
                .map(RunningResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.RUNNING_INFO_SUCCESS, response));
    }

    @Operation(summary = "러닝 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RunningResponse>> getRunningById(@PathVariable Long id) {
        Running running = runningService.getRunningById(id);
        RunningResponse response = new RunningResponse(running);
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.RUNNING_INFO_SUCCESS, response));
    }

    @Operation(summary = "러닝 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRunning(@PathVariable Long id) {
        runningService.deleteRunning(id);
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.RUNNING_DELETE_SUCCESS));
    }
    @Operation(summary = "유저 러닝 기록 조회 (한 달)")
    @GetMapping("/user/{userId}/monthly")
    public ResponseEntity<ApiResponse<List<RunningResponse>>> getMonthlyRunningsByUserId(@PathVariable Long userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);
        List<Running> runnings = runningService.getRunningsByUserIdAndDateRange(userId, oneMonthAgo, now);
        List<RunningResponse> response = runnings.stream()
                .map(RunningResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.RUNNING_INFO_SUCCESS, response));
    }
}
