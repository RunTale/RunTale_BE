package com.likelion.runtale.domain.tier.controller;

import com.likelion.runtale.common.ApiResponse;
import com.likelion.runtale.common.response.SuccessMessage;
import com.likelion.runtale.domain.tier.dto.TierResponse;
import com.likelion.runtale.domain.tier.dto.UserTierInfo;
import com.likelion.runtale.domain.tier.service.TierService;
import com.likelion.runtale.domain.user.entity.User;
import com.likelion.runtale.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "tier", description = "Tier 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tier")
public class TierController {

    private final UserService userService;
    private final TierService tierService;

    @Operation(summary = "유저의 티어 및 상위 퍼센트 조회")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<TierResponse>> getUserTierAndPercentile(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        TierResponse response = new TierResponse(
                user.getNickname(),
                user.getTier().getName(),
                user.getTier().getDescription(),
                user.getTier().getImageUrl(),
                user.getPercentile(),
                user.getProgress()
        );
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.TIER_INFO_SUCCESS, response));
    }

    @Operation(summary = "티어 할당")
    @PostMapping("/assign")
    public ResponseEntity<ApiResponse<Void>> assignTiersToUsers(@RequestParam int year, @RequestParam int month) {
        tierService.assignTiersToUsers(year, month);
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.TIER_ASSIGN_SUCCESS));
    }

    @Operation(summary = "전체 유저의 티어 및 상위 퍼센트 조회")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserTierInfo>>> getAllUsersWithTiers() {
        List<UserTierInfo> response = tierService.getAllUsersWithTiers();
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.TIER_INFO_SUCCESS, response));
    }
}
