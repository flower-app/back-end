package bohun.flower.app.controller;

import bohun.flower.app.dto.season.CreateSeasonRequestDto;
import bohun.flower.app.dto.season.SeasonDto;
import bohun.flower.app.service.season.SeasonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Season management", description = "Endpoints for managing seasons")
@RequiredArgsConstructor
@RestController
@RequestMapping("/seasons")
public class SeasonController {
    private final SeasonService seasonService;

    @GetMapping
    @Operation(description = "Get all seasons")
    @ApiResponse(responseCode = "200", description = "All seasons")
    public List<SeasonDto> getAll(Pageable pageable) {
        return seasonService.findAll(pageable);
    }

    @GetMapping("/{seasonId}")
    @Operation(description = "get season by id")
    @ApiResponse(responseCode = "200", description = "season by Id")
    public SeasonDto getSeasonById(@PathVariable Long seasonId) {
        return seasonService.getSeasonById(seasonId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(description = "Create new season")
    @ApiResponse(responseCode = "201", description = "Create new season")
    @ResponseStatus(HttpStatus.CREATED)
    public SeasonDto createSeason(@RequestBody @Valid CreateSeasonRequestDto createSeasonRequestDto) {
        return seasonService.save(createSeasonRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{seasonId}")
    @Operation(description = "Update season")
    @ApiResponse(responseCode = "200", description = "Update season")
    public SeasonDto updateSeason(@PathVariable Long seasonId,
                              @RequestBody CreateSeasonRequestDto createSeasonRequestDto) {
        return seasonService.updateSeason(seasonId, createSeasonRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{seasonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete season by Id")
    @ApiResponse(responseCode = "200", description = "Delete season")
    public void deleteSeason(@PathVariable Long seasonId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().startsWith("admin")) seasonService.deleteSeason(seasonId);
    }
}
