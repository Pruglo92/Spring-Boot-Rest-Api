package com.vkr.webapp.controllers;

import com.vkr.webapp.dto.SavedResultDto;
import com.vkr.webapp.exception.SavedResultNotFoundException;
import com.vkr.webapp.mapper.SavedResultMapper;
import com.vkr.webapp.service.SavedResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/admin")
@Transactional(readOnly = true)
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SavedResultsController {

    private final SavedResultService savedResultService;

    private final SavedResultMapper savedResultMapper;

    @Operation(summary = "Get saved result list",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @GetMapping("/saved-result")
    public ResponseEntity<List<SavedResultDto>> getSavedResults() {
        final var savedResultDtoList = savedResultService.getAll().stream()
                .map(savedResultMapper::toDto)
                .toList();
        return ResponseEntity.ok(savedResultDtoList);
    }

    @Operation(summary = "Get user by user Id",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @GetMapping("/saved-result/{Saved-resultId}")
    public ResponseEntity<SavedResultDto> getSavedResultById(
            @Parameter(in = ParameterIn.PATH,
                    description = "id of saved-result",
                    required = true,
                    schema = @Schema()) @PathVariable("Saved-resultId") final Long savedResultId) {
            final var result = savedResultService.findById(savedResultId)
                    .map(savedResultMapper :: toDto)
                    .orElseThrow(() -> new SavedResultNotFoundException("Saved result id " + savedResultId + " was not found"));
            return ResponseEntity.ok(result);
    }

    @Operation(summary = "Update save result. Only required fields can be specified",
            description = "This can only be done by the logged in user. Use ROLE_USER or/and ROLE_ADMIN instead of \"string\"",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @Transactional
    @PutMapping("/saved-result/{Saved-resultId}")
    public ResponseEntity<SavedResultDto> updateSavedResult(
            @Parameter(in = ParameterIn.PATH, description = "id of saved result to update",
                    required = true,
                    schema = @Schema()) @PathVariable("Saved-resultId") final Long savedResultId,
            @Parameter(in = ParameterIn.DEFAULT,
                    required = true,
                    schema = @Schema()) @Valid @RequestBody SavedResultDto savedResultDto) {
            var targetResult = savedResultService.findById(savedResultId)
                .orElseThrow(() -> new SavedResultNotFoundException("Saved result id " + savedResultId + " was not found"));
            var updateResult = savedResultService.update(targetResult, savedResultDto);
            return ResponseEntity.ok(updateResult);
    }
}
